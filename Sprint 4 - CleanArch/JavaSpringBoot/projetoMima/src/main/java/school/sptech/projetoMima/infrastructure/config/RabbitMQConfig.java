package school.sptech.projetoMima.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.password-recovery.queue}")
    private String passwordRecoveryQueue;

    @Value("${app.rabbitmq.password-recovery.exchange}")
    private String passwordRecoveryExchange;

    @Value("${app.rabbitmq.password-recovery.routing-key}")
    private String passwordRecoveryRoutingKey;

    @Bean
    public Queue passwordRecoveryQueue() {
        return new Queue(passwordRecoveryQueue, true);
    }

    @Bean
    public DirectExchange passwordRecoveryExchange() {
        return new DirectExchange(passwordRecoveryExchange, true, false);
    }

    @Bean
    public Binding passwordRecoveryBinding() {
        return BindingBuilder
                .bind(passwordRecoveryQueue())
                .to(passwordRecoveryExchange())
                .with(passwordRecoveryRoutingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
