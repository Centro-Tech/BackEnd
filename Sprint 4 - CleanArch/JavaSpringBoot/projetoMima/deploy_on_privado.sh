#!/bin/bash
set -e

echo "🚀 Iniciando deploy do backend..."

# Diretórios
BACKEND_DIR="$HOME/backend"
JAVA_DIR="$BACKEND_DIR/JavaSpringBoot/projetoMima"
CONSUMER_DIR="$BACKEND_DIR/Consumer - RabbitMQ"
NGINX_DIR="$BACKEND_DIR/nginx"

cd "$JAVA_DIR"

# 1. Configurar Nginx
echo "📝 Configurando Nginx..."
if [ -f "$NGINX_DIR/backend.conf" ]; then
    sudo cp "$NGINX_DIR/backend.conf" /etc/nginx/sites-available/default
    sudo nginx -t && sudo systemctl reload nginx
    echo "✅ Nginx configurado e recarregado"
else
    echo "⚠️ Arquivo nginx/backend.conf não encontrado, pulando configuração do nginx"
fi

# 2. Build do projeto Java
echo "🔨 Compilando projeto Spring Boot..."
./mvnw clean package -DskipTests

# 3. Parar container antigo
echo "🛑 Parando containers antigos..."
sudo docker-compose down || true

# 4. Subir nova versão
echo "🐳 Iniciando novos containers..."
sudo docker-compose up -d --build

# 5. Aguardar backend estar pronto
echo "⏳ Aguardando backend inicializar..."
sleep 15

# 6. Verificar saúde
if curl -f http://localhost:8080/actuator/health >/dev/null 2>&1; then
    echo "✅ Backend está respondendo"
else
    echo "⚠️ Backend pode ainda estar inicializando"
fi

# 7. Subir consumers RabbitMQ
echo "🐰 Iniciando consumers RabbitMQ..."

# Consumer de comprovantes
if [ -d "$CONSUMER_DIR/Envio de Comprovante" ]; then
    cd "$CONSUMER_DIR/Envio de Comprovante"
    sudo RABBITMQ_HOST=rabbitmq docker-compose down || true
    sudo RABBITMQ_HOST=rabbitmq docker-compose up -d --build
    echo "✅ Consumer de comprovantes iniciado"
fi

# Consumer de recuperação de senha
if [ -d "$CONSUMER_DIR/Recuperação de Senha" ]; then
    cd "$CONSUMER_DIR/Recuperação de Senha"
    sudo RABBITMQ_HOST=rabbitmq docker-compose -f docker-compose.consumer.yml down || true
    sudo RABBITMQ_HOST=rabbitmq docker-compose -f docker-compose.consumer.yml up -d --build
    echo "✅ Consumer de recuperação de senha iniciado"
fi

echo "✅ Deploy concluído com sucesso!"
echo "📊 Containers em execução:"
sudo docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
