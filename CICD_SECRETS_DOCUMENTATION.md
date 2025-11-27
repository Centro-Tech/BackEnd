# 🔐 Secrets Necessários para CI/CD - Mima Store

Este documento lista todos os **GitHub Secrets** que precisam ser configurados no repositório para que o CI/CD funcione corretamente.

## 📍 Como adicionar secrets no GitHub

1. Vá para o repositório no GitHub
2. Clique em **Settings** > **Secrets and variables** > **Actions**
3. Clique em **New repository secret**
4. Adicione cada secret abaixo

---

## 🔑 Secrets Obrigatórios

### **Chaves SSH**

| Secret | Descrição | Exemplo |
|--------|-----------|---------|
| `EC2_SSH_KEY_AB` | Chave privada SSH para acessar zona 1 (hosts A e B) | Conteúdo completo do arquivo `.pem` |
| `EC2_SSH_KEY_CD` | Chave privada SSH para acessar zona 2 (hosts C e D) | Conteúdo completo do arquivo `.pem` |

> ⚠️ **Importante**: Cole o conteúdo completo da chave privada, incluindo:
> ```
> -----BEGIN RSA PRIVATE KEY-----
> [conteúdo da chave]
> -----END RSA PRIVATE KEY-----
> ```

---

### **Hosts AWS (IPs ou DNS)**

| Secret | Descrição | Exemplo |
|--------|-----------|---------|
| `REMOTE_HOST` | Host público A (zona 1) | `ec2-54-123-45-67.compute-1.amazonaws.com` |
| `REMOTE_HOST_B` | Host privado B (zona 1) | `10.0.1.10` |
| `REMOTE_HOST_C` | Host público C (zona 2) | `ec2-54-234-56-78.compute-1.amazonaws.com` |
| `REMOTE_HOST_D` | Host privado D (zona 2) | `10.0.2.10` |
| `REMOTE_USER` | Usuário SSH para conexão (geralmente `ubuntu` ou `ec2-user`) | `ubuntu` |

---

### **RabbitMQ (Variáveis de Ambiente AWS)**

Os consumers Python precisam se conectar ao RabbitMQ. Configure estas variáveis **nas instâncias AWS** via `.env` ou docker-compose:

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| `RABBITMQ_HOST` | IP privado onde o RabbitMQ roda | `localhost` ou IP privado |
| `RABBITMQ_USER` | Usuário do RabbitMQ | `myuser` |
| `RABBITMQ_PASSWORD` | Senha do RabbitMQ | `secret` |

> 💡 **Dica**: Se o RabbitMQ rodar na mesma instância que os consumers, use `localhost`. Caso contrário, use o IP privado da instância do RabbitMQ.

---

## 📦 O que o CI/CD faz automaticamente

### **Backend Java**
1. ✅ Compila o projeto Maven
2. ✅ Gera o arquivo `.jar`
3. ✅ Copia para as instâncias AWS (zonas 1 e 2)
4. ✅ Executa deploy via Docker Compose

### **Consumers Python** (NOVO!)
1. ✅ Valida sintaxe dos arquivos Python
2. ✅ Envia código dos consumers para AWS
3. ✅ Faz build das imagens Docker
4. ✅ Inicia containers automaticamente via Docker Compose
5. ✅ Conecta à rede `mima-network` para comunicação com RabbitMQ

---

## 🐳 Estrutura dos Consumers na AWS

Após o deploy, os consumers estarão rodando como containers Docker:

```
/home/ubuntu/backend/
├── Consumer - RabbitMQ/
│   ├── Envio de Comprovante/
│   │   ├── Dockerfile
│   │   ├── requirements.txt
│   │   ├── docker-compose.yml
│   │   └── comprovante_venda_consumer.py
│   └── Recuperação de Senha/
│       ├── Dockerfile
│       ├── requirements.txt
│       ├── docker-compose.consumer.yml
│       └── password_recovery_consumer_backup.py
```

### **Comandos úteis na AWS**

```bash
# Ver logs do consumer de comprovantes
sudo docker logs -f consumer-comprovantes

# Ver logs do consumer de recuperação de senha
sudo docker logs -f consumer-password-recovery

# Reiniciar consumer de comprovantes
cd ~/backend/Consumer\ -\ RabbitMQ/Envio\ de\ Comprovante/
sudo docker compose restart

# Reiniciar consumer de recuperação de senha
cd ~/backend/Consumer\ -\ RabbitMQ/Recuperação\ de\ Senha/
sudo docker compose -f docker-compose.consumer.yml restart

# Ver status de todos os containers
sudo docker ps

# Ver rede mima-network
sudo docker network inspect mima-network
```

---

## 🔍 Troubleshooting

### **Consumer não conecta no RabbitMQ**
1. Verifique se `RABBITMQ_HOST` está correto no docker-compose
2. Certifique-se que o RabbitMQ está rodando: `sudo docker ps | grep rabbitmq`
3. Teste conexão: `telnet <RABBITMQ_HOST> 5672`

### **Consumer não envia emails**
1. Verifique credenciais SMTP no docker-compose
2. Veja logs: `sudo docker logs consumer-comprovantes`
3. Teste conexão SMTP: `telnet smtp.maileroo.com 587`

### **Rede mima-network não existe**
O script de deploy cria automaticamente, mas se precisar criar manualmente:
```bash
sudo docker network create mima-network
```

---

## ✅ Checklist Final

Antes de fazer push para `main`, verifique:

- [ ] Todos os secrets estão configurados no GitHub
- [ ] Chaves SSH estão corretas e com permissões adequadas
- [ ] IPs/DNS dos hosts estão atualizados
- [ ] RabbitMQ está rodando na AWS
- [ ] Variáveis de ambiente SMTP estão corretas
- [ ] Rede `mima-network` existe na AWS

---

**📅 Última atualização**: Novembro 2025  
**👥 Mantenedores**: Centro-Tech
