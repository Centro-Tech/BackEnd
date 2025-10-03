import os
import json
import re
import time
import smtplib
import argparse
import traceback
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

try:
    from dotenv import load_dotenv  # type: ignore
    load_dotenv()
except Exception:
    pass

EMAIL_REGEX = re.compile(r"^[^@\s]+@[^@\s]+\.[^@\s]+$")


def env_bool(name: str, default: str = "0") -> bool:
    return os.getenv(name, default).strip().lower() in ("1", "true", "yes", "on")


class PasswordRecoveryConsumer:
    # Esta classe pega mensagens da fila e envia email de recuperacao de senha
    # A mensagem tem que ter email, nome, token e resetUrl

    def __init__(self):
        # Configurações do RabbitMQ
        self.rabbitmq_host = os.getenv('RABBITMQ_HOST', 'localhost')
        self.rabbitmq_port = int(os.getenv('RABBITMQ_PORT', '5672'))
        self.rabbitmq_user = os.getenv('RABBITMQ_USER', 'guest')
        self.rabbitmq_password = os.getenv('RABBITMQ_PASSWORD', 'guest')
        self.rabbitmq_vhost = os.getenv('RABBITMQ_VHOST', '/')
        self.queue_name = os.getenv('PASSWORD_RECOVERY_QUEUE', 'password.recovery.queue')
        self.prefetch_count = int(os.getenv('RABBITMQ_PREFETCH', '5'))
        self.reconnect_delay = int(os.getenv('RABBITMQ_RECONNECT_DELAY', '5'))

        # Configurações do email
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

        # URLs do sistema
        self.frontend_reset_url = os.getenv('FRONTEND_RESET_URL', 'https://mimastore.com/reset-password')
        self.backend_base_url = os.getenv('BACKEND_BASE_URL', 'http://localhost:8080')
        self.force_backend_auth_link = env_bool('FORCE_BACKEND_AUTH_LINK', '0')

        # Opções do email
        self.include_plain = True
        self.include_html = True

        # Se SSL e TLS estao ativados, usa só SSL
        if self.use_ssl and self.use_starttls:
            print("[SMTP][WARN] SSL e TLS ativados juntos, usando só SSL")
            self.use_starttls = False

    # Monta os links do email
    def build_links(self, token: str, provided_reset_url: str | None):
        if provided_reset_url and provided_reset_url.strip():
            frontend_link = provided_reset_url.strip()
        else:
            frontend_link = f"{self.frontend_reset_url}?token={token}"

        backend_auth_link = f"{self.backend_base_url}/usuarios/recuperar-senha/autenticar?token={token}"
        backend_reset_endpoint = f"{self.backend_base_url}/usuarios/redefinir-senha"

        primary_link = backend_auth_link if self.force_backend_auth_link else frontend_link

        return {
            'primary_link': primary_link,
            'frontend_link': frontend_link,
            'backend_auth_link': backend_auth_link,
            'backend_reset_endpoint': backend_reset_endpoint
        }

    # Conecta no servidor de email e envia
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
                        print("[SMTP][INFO] Sem credenciais definidas ou auth desabilitado; enviando sem LOGIN.")

                server.sendmail(self.email_address, [email], msg.as_string())
                server.quit()
                print(f"[EMAIL][OK] Enviado para {email} (tentativa {attempt})")
                return True
            except Exception as e:
                last_error = e
                print(f"[SMTP][ERRO] Tentativa {attempt} falhou: {e}")
                if self.smtp_debug:
                    traceback.print_exc()
                time.sleep(self.smtp_retry_delay)
        print(f"[SMTP][FATAL] Falha após {self.smtp_retry_attempts} tentativas. Último erro: {last_error}")
        return False

    # Envia o email de recuperacao
    def send_recovery_email(self, payload: dict) -> bool:
        email = payload.get('email')
        nome = payload.get('nome') or 'Usuário'
        token = payload.get('token')
        reset_url = payload.get('resetUrl')

        if not email or not EMAIL_REGEX.match(email):
            print(f"[EMAIL][INVALIDO] Ignorando envio para '{email}'")
            return False
        if not token:
            print("[EMAIL][ERRO] Payload sem token")
            return False

        links = self.build_links(token, reset_url)

        subject = 'Recuperação de Senha - Mima Store'
        from_header = f"{self.sender_name} <{self.email_address}>"

        msg = MIMEMultipart('alternative')
        msg['From'] = from_header
        msg['To'] = email
        msg['Subject'] = subject

        plain_body = f"""Olá {nome},\n\nVocê solicitou a recuperação de senha da sua conta na Mima Store.\n\nToken: {token}\n\nAcesse para redefinir:\n{links['primary_link']}\n\nLinks alternativos:\n- Página de redefinição: {links['frontend_link']}\n- Autenticar e obter JWT (backend): {links['backend_auth_link']}\n- Endpoint de redefinição (POST JSON): {links['backend_reset_endpoint']}\n\nSe você não fez esta solicitação, ignore este e-mail.\n\nEquipe Mima Store\n"""

        html_body = f"""<html><body style='font-family:Arial,sans-serif;color:#222;'>
<h2 style='color:#005bbb;'>Recuperação de Senha</h2>
<p>Olá <strong>{nome}</strong>,</p>
<p>Você solicitou a recuperação de senha da sua conta na <strong>Mima Store</strong>.</p>
<p style='margin:18px 0;'>
  <a href='{links['primary_link']}' style='background:#005bbb;color:#fff;padding:12px 20px;text-decoration:none;border-radius:4px;'>Redefinir Senha</a>
</p>
<p style='font-size:13px;'>Se o botão não funcionar, copie este link:<br>
<code style='word-break:break-all;'>{links['primary_link']}</code></p>
<hr style='border:none;border-top:1px solid #ddd;margin:24px 0;' />
<p style='font-size:13px;margin:0 0 8px 0;'>Links alternativos:</p>
<ul style='font-size:13px;line-height:1.4;'>
  <li>Página de redefinição: <code style='word-break:break-all;'>{links['frontend_link']}</code></li>
  <li>Autenticar e obter JWT: <code style='word-break:break-all;'>{links['backend_auth_link']}</code></li>
  <li>Endpoint POST /usuarios/redefinir-senha: <code style='word-break:break-all;'>{links['backend_reset_endpoint']}</code></li>
</ul>
<p style='font-size:12px;color:#555;'>Se você não fez esta solicitação, ignore este e-mail.</p>
<p style='font-size:12px;color:#777;'>&copy; Mima Store</p>
</body></html>"""

        if self.include_plain:
            msg.attach(MIMEText(plain_body, 'plain', 'utf-8'))
        if self.include_html:
            msg.attach(MIMEText(html_body, 'html', 'utf-8'))

        print(f"[EMAIL][ENVIANDO] Para {email} via {self.smtp_server}:{self.smtp_port} SSL={self.use_ssl} STARTTLS={self.use_starttls}")
        return self._smtp_connect_and_send(email, msg)

    # Processa mensagem que chega da fila
    def process_message(self, ch, method, properties, body: bytes):  # type: ignore
        try:
            message = json.loads(body.decode('utf-8'))
            if not message.get('email') or not message.get('token'):
                raise ValueError('Mensagem inválida: requer email e token')
            self.send_recovery_email(message)
            ch.basic_ack(delivery_tag=method.delivery_tag)
        except Exception as e:
            print(f"[QUEUE][ERRO] {e} | RAW={body!r}")
            traceback.print_exc()
            ch.basic_ack(delivery_tag=method.delivery_tag)

    # Fica rodando e esperando mensagens da fila
    def start_consuming(self):
        import pika  # importa aqui pra nao dar erro se nao tiver instalado
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
                print('[RABBITMQ][PRONTO] Aguardando mensagens de recuperação de senha...')
                channel.start_consuming()
            except KeyboardInterrupt:
                print('[RABBITMQ][STOP] Interrompido pelo usuário.')
                break
            except Exception as e:
                print(f"[RABBITMQ][ERRO] {e}. Reconnecting in {self.reconnect_delay}s...")
                time.sleep(self.reconnect_delay)


def send_test_email(recipient: str):
    consumer = PasswordRecoveryConsumer()
    fake_token = 'TOKEN_TESTE_ABC123'
    payload = {
        'userId': 0,
        'email': recipient,
        'nome': 'Teste',
        'token': fake_token,
        'resetUrl': f"{consumer.frontend_reset_url}?token={fake_token}"
    }
    consumer.send_recovery_email(payload)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Consumidor de recuperação de senha - Mima Store')
    parser.add_argument('--test', metavar='email', help='Envia um email de teste e sai')
    args = parser.parse_args()

    if args.test:
        send_test_email(args.test)
    else:
        PasswordRecoveryConsumer().start_consuming()
