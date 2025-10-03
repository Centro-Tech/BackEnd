import os
import json
import re
import time
import smtplib
import traceback
from datetime import datetime
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart


try:
    from dotenv import load_dotenv  # type: ignore
    # Tenta carregar primeiro um .env local; se existir variavel BASE_ENV_PATH carrega também
    load_dotenv()
    base_env_path = os.getenv('BASE_ENV_PATH')
    # Permite apontar para o .env central (ex: Sprint 5 - Consumer Python para enviar E-mails/.env)
    possible_external = [
        base_env_path,
        os.path.join('..', 'Sprint 5 - Consumer Python para enviar E-mails', '.env'),
        os.path.join('..', '.env')
    ]
    for p in possible_external:
        if p and os.path.isfile(p):
            load_dotenv(p, override=False)
except Exception:
    pass

EMAIL_REGEX = re.compile(r"^[^@\s]+@[^@\s]+\.[^@\s]+$")


def env_bool(name: str, default: str = "0") -> bool:
    return os.getenv(name, default).strip().lower() in ("1", "true", "yes", "on")


class ComprovanteVendaConsumer:
    # Junta configs e faz o trabalho
    def __init__(self):
    # RabbitMQ
        self.rabbitmq_host = os.getenv('RABBITMQ_HOST', 'localhost')
        self.rabbitmq_port = int(os.getenv('RABBITMQ_PORT', '5672'))
        self.rabbitmq_user = os.getenv('RABBITMQ_USER', 'guest')
        self.rabbitmq_password = os.getenv('RABBITMQ_PASSWORD', 'guest')
        self.rabbitmq_vhost = os.getenv('RABBITMQ_VHOST', '/')
        self.queue_name = os.getenv('COMPROVANTE_VENDA_QUEUE', 'comprovante.venda.queue')
        self.prefetch_count = int(os.getenv('RABBITMQ_PREFETCH', '5'))
        self.reconnect_delay = int(os.getenv('RABBITMQ_RECONNECT_DELAY', '5'))
        print(f"[RABBITMQ][DEBUG] host={self.rabbitmq_host} port={self.rabbitmq_port} user={self.rabbitmq_user} vhost={self.rabbitmq_vhost} queue={self.queue_name}")

    # SMTP
        self.smtp_server = os.getenv('SMTP_SERVER', 'localhost')
        self.smtp_port = int(os.getenv('SMTP_PORT', '587'))
        self.use_starttls = env_bool('SMTP_USE_TLS', '1')
        self.use_ssl = env_bool('SMTP_USE_SSL', '0')
        self.email_address = os.getenv('EMAIL_FROM', 'no-reply@mimastore.com')
        self.sender_name = os.getenv('SENDER_NAME', 'Mima Store')
        self.smtp_user = os.getenv('SMTP_USER')
        self.smtp_pass = os.getenv('SMTP_PASS')
        self.smtp_auth_required = env_bool('SMTP_AUTH_REQUIRED', '1')
        self.smtp_debug = env_bool('SMTP_DEBUG', '0')
        self.smtp_retry_attempts = int(os.getenv('SMTP_RETRY_ATTEMPTS', '3'))
        self.smtp_retry_delay = float(os.getenv('SMTP_RETRY_DELAY', '2'))

    # Dados pra mostrar no rodapé
        self.loja_nome = os.getenv('LOJA_NOME', 'Mima Store')
        self.loja_endereco = os.getenv('LOJA_ENDERECO', 'R. das Pitangueiras, 470 - Jardim, Santo André - SP, 09090-150')
        self.loja_telefone = os.getenv('LOJA_TELEFONE', '(11) 93906-0902')
        self.loja_cnpj = os.getenv('LOJA_CNPJ', '00.000.000/0000-00')

    # Se manda texto / html
        self.include_plain = True
        self.include_html = True

        if self.use_ssl and self.use_starttls:
            print("[SMTP][WARN] SSL e TLS ativados juntos, usando só SSL")
            self.use_starttls = False

    # Texto simples
    def _build_plain(self, data: dict) -> str:
        venda_id = data.get('vendaId', 0)
        valor_total = data.get('valorTotal', 0.0)
        data_venda = data.get('dataVenda', 'N/A')
        cliente = data.get('cliente', {})
        funcionario = data.get('funcionario', {})
        itens = data.get('itens', [])

        lines = [
            f"{self.loja_nome.upper()}",
            "COMPROVANTE DE COMPRA",
            "",
            f"Venda: #{venda_id:06d}",
            f"Data: {data_venda} - {datetime.now().strftime('%H:%M')}",
            f"Cliente: {cliente.get('nome','Cliente')} ({cliente.get('cpf','N/A')})",
            f"Atendido por: {funcionario.get('nome','N/A')} ({funcionario.get('cargo','N/A')})",
            "",
            "Itens:"
        ]

        for item in itens:
            lines.append(
                f"- {item.get('nome','Item')} (cod {item.get('codigo','N/A')}): {item.get('quantidade',1)} x R$ {item.get('precoUnitario',0.0):.2f} = R$ {item.get('subtotal',0.0):.2f}"  # noqa: E501
            )

        lines += [
            "",
            f"TOTAL: R$ {valor_total:.2f}",
            "",
            "Obrigado pela sua compra!",
            self.loja_nome,
            self.loja_endereco,
            f"Tel: {self.loja_telefone}",
            f"CNPJ: {self.loja_cnpj}"
        ]
        return "\n".join(lines)

    def _build_html(self, data: dict) -> str:  # HTML do email
        venda_id = data.get('vendaId', 0)
        valor_total = data.get('valorTotal', 0.0)
        data_venda = data.get('dataVenda', 'N/A')
        cliente = data.get('cliente', {})
        funcionario = data.get('funcionario', {})
        itens = data.get('itens', [])

        itens_rows = []
        for item in itens:
            detalhes_bits = []
            for k in ('categoria', 'cor', 'tamanho', 'material'):
                if item.get(k):
                    detalhes_bits.append(f"{k.title()}: {item[k]}")
            detalhes_html = f"<br><small style='color:#666;'>{' | '.join(detalhes_bits)}</small>" if detalhes_bits else ""
            itens_rows.append(f"""
            <tr>
              <td style='padding:8px;border-bottom:1px solid #eee;'>
                <strong>{item.get('nome','Item')}</strong><br>
                <small>Código: {item.get('codigo','N/A')}</small>
                {detalhes_html}
              </td>
              <td style='padding:8px;border-bottom:1px solid #eee;text-align:center;'>{item.get('quantidade',1)}</td>
              <td style='padding:8px;border-bottom:1px solid #eee;text-align:right;'>R$ {item.get('precoUnitario',0.0):.2f}</td>
              <td style='padding:8px;border-bottom:1px solid #eee;text-align:right;'><strong>R$ {item.get('subtotal',0.0):.2f}</strong></td>
            </tr>
            """)

        itens_html = "".join(itens_rows) or "<tr><td colspan='4' style='padding:12px;text-align:center;color:#666;'>Nenhum item.</td></tr>"

        return f"""
<!DOCTYPE html>
<html lang='pt-BR'>
<head><meta charset='utf-8'><title>Comprovante #{venda_id:06d}</title></head>
<body style='font-family:Arial,sans-serif;background:#f5f5f5;margin:0;padding:24px;'>
  <div style='max-width:640px;margin:0 auto;background:#fff;border-radius:8px;overflow:hidden;border:1px solid #e0e0e0;'>
    <div style='background:#863e76;color:#fff;padding:24px;'>
      <h1 style='margin:0;font-size:22px;'>{self.loja_nome}</h1>
      <p style='margin:4px 0 0 0;font-size:14px;'>Comprovante de Compra</p>
    </div>
    <div style='padding:24px;'>
      <h2 style='margin:0 0 12px 0;font-size:18px;color:#222;'>Dados da Venda</h2>
      <table style='width:100%;font-size:14px;margin:0 0 16px 0;'>
        <tr><td style='padding:4px 0;'>Número:</td><td style='text-align:right;'>#{venda_id:06d}</td></tr>
        <tr><td style='padding:4px 0;'>Data/Hora:</td><td style='text-align:right;'>{data_venda} - {datetime.now().strftime('%H:%M')}</td></tr>
        <tr><td style='padding:4px 0;'>Cliente:</td><td style='text-align:right;'>{cliente.get('nome','Cliente')}</td></tr>
        <tr><td style='padding:4px 0;'>CPF:</td><td style='text-align:right;'>{cliente.get('cpf','N/A')}</td></tr>
        <tr><td style='padding:4px 0;'>Atendente:</td><td style='text-align:right;'>{funcionario.get('nome','N/A')} ({funcionario.get('cargo','N/A')})</td></tr>
      </table>
      <h3 style='margin:16px 0 8px 0;font-size:16px;color:#222;'>Itens</h3>
      <table style='width:100%;border-collapse:collapse;font-size:13px;'>
        <thead>
          <tr style='background:#f1f5f9;'>
            <th style='text-align:left;padding:8px;border-bottom:2px solid #e2e8f0;'>Produto</th>
            <th style='text-align:center;padding:8px;border-bottom:2px solid #e2e8f0;'>Qtd</th>
            <th style='text-align:right;padding:8px;border-bottom:2px solid #e2e8f0;'>Unit.</th>
            <th style='text-align:right;padding:8px;border-bottom:2px solid #e2e8f0;'>Total</th>
          </tr>
        </thead>
        <tbody>{itens_html}</tbody>
      </table>
            <div style='margin:24px 0 0 0;text-align:right;background:#f7e9f3;padding:16px;border-radius:6px;'>
                <span style='font-size:14px;color:#863e76;'>Total</span>
                <div style='font-size:24px;color:#863e76;font-weight:bold;'>R$ {valor_total:.2f}</div>
      </div>
      <div style='margin:28px 0 0 0;text-align:center;font-size:13px;color:#444;background:#f8f9fa;padding:16px;border-radius:6px;'>
        Obrigado pela sua compra! Volte sempre.
      </div>
    </div>
    <div style='background:#222;color:#ddd;text-align:center;padding:16px;font-size:12px;'>
      <div><strong>{self.loja_nome}</strong></div>
      <div>{self.loja_endereco}</div>
      <div>Telefone: {self.loja_telefone}</div>
      <div>CNPJ: {self.loja_cnpj}</div>
    </div>
  </div>
</body>
</html>
"""

    # Tenta enviar (com várias tentativas)
    def _smtp_connect_and_send(self, email: str, msg: MIMEMultipart) -> bool:
        last_error = None
        for attempt in range(1, self.smtp_retry_attempts + 1):
            try:
                print(f"[SMTP][TENTATIVA {attempt}/{self.smtp_retry_attempts}] Conectando {self.smtp_server}:{self.smtp_port} SSL={self.use_ssl} STARTTLS={self.use_starttls}")
                if self.use_ssl:
                    server = smtplib.SMTP_SSL(self.smtp_server, self.smtp_port, timeout=30)
                else:
                    server = smtplib.SMTP(self.smtp_server, self.smtp_port, timeout=30)
                if self.smtp_debug:
                    server.set_debuglevel(1)
                server.ehlo()
                if self.use_starttls and not self.use_ssl:
                    try:
                        server.starttls()
                        server.ehlo()
                    except Exception as e_tls:
                        raise RuntimeError(f"Falha no STARTTLS: {e_tls}")
                if self.smtp_auth_required and self.smtp_user and self.smtp_pass:
                    try:
                        server.login(self.smtp_user, self.smtp_pass)
                    except smtplib.SMTPServerDisconnected as disc:
                        raise RuntimeError(f"Servidor desconectou durante login: {disc}")
                    except smtplib.SMTPAuthenticationError as auth_err:
                        raise RuntimeError(f"Falha de autenticação SMTP: {auth_err}")
                else:
                    if not (self.smtp_user and self.smtp_pass):
                        print("[SMTP][INFO] Enviando sem autenticação (credenciais ausentes ou desativado)")
                server.sendmail(self.email_address, [email], msg.as_string())
                server.quit()
                print(f"[EMAIL][OK] Comprovante enviado para {email} (tentativa {attempt})")
                return True
            except Exception as e:
                last_error = e
                print(f"[SMTP][ERRO] Tentativa {attempt} falhou: {e}")
                if self.smtp_debug:
                    traceback.print_exc()
                time.sleep(self.smtp_retry_delay)
        print(f"[SMTP][FATAL] Falha após {self.smtp_retry_attempts} tentativas. Último erro: {last_error}")
        return False

    # Monta e envia
    def send_comprovante_email(self, data: dict) -> bool:
        cliente = data.get('cliente', {})
        email = cliente.get('email')
        venda_id = data.get('vendaId', 0)
        if not email or not EMAIL_REGEX.match(email):
            print(f"[EMAIL][INVALIDO] Ignorando email='{email}' venda={venda_id}")
            return False

        subject = f"Comprovante de Compra #{venda_id:06d} - {self.loja_nome}"
        from_header = f"{self.sender_name} <{self.email_address}>"
        msg = MIMEMultipart('alternative')
        msg['From'] = from_header
        msg['To'] = email
        msg['Subject'] = subject

        if self.include_plain:
            msg.attach(MIMEText(self._build_plain(data), 'plain', 'utf-8'))
        if self.include_html:
            msg.attach(MIMEText(self._build_html(data), 'html', 'utf-8'))

        print(f"[EMAIL][ENVIANDO] Comprovante venda #{venda_id:06d} -> {email}")
        return self._smtp_connect_and_send(email, msg)

    # Quando chega algo da fila
    def process_message(self, ch, method, properties, body: bytes):  # type: ignore
        try:
            payload = json.loads(body.decode('utf-8'))
            if not payload.get('cliente', {}).get('email'):
                raise ValueError('Payload sem cliente.email')
            self.send_comprovante_email(payload)
            ch.basic_ack(delivery_tag=method.delivery_tag)
        except Exception as e:
            print(f"[QUEUE][ERRO] {e} | RAW={body!r}")
            if self.smtp_debug:
                traceback.print_exc()
            ch.basic_ack(delivery_tag=method.delivery_tag)

    # Loop infinito de consumo
    def start_consuming(self):
        import pika  # type: ignore
        while True:
            try:
                print(f"[RABBITMQ][CONECTANDO] {self.rabbitmq_host}:{self.rabbitmq_port} vhost='{self.rabbitmq_vhost}' queue='{self.queue_name}'")
                credentials = pika.PlainCredentials(self.rabbitmq_user, self.rabbitmq_password)
                params = pika.ConnectionParameters(
                    host=self.rabbitmq_host,
                    port=self.rabbitmq_port,
                    virtual_host=self.rabbitmq_vhost,
                    credentials=credentials,
                    heartbeat=30,
                    blocked_connection_timeout=60
                )
                connection = pika.BlockingConnection(params)
                channel = connection.channel()
                channel.queue_declare(queue=self.queue_name, durable=True)
                channel.basic_qos(prefetch_count=self.prefetch_count)
                channel.basic_consume(queue=self.queue_name, on_message_callback=self.process_message)
                print('[RABBITMQ][PRONTO] Aguardando comprovantes de venda...')
                channel.start_consuming()
            except KeyboardInterrupt:
                print('[RABBITMQ][STOP] Interrompido pelo usuário.')
                break
            except Exception as e:
                print(f"[RABBITMQ][ERRO] {e}. Reconnecting in {self.reconnect_delay}s...")
                time.sleep(self.reconnect_delay)


if __name__ == '__main__':
    # Inicia direto consumindo a fila
    ComprovanteVendaConsumer().start_consuming()
