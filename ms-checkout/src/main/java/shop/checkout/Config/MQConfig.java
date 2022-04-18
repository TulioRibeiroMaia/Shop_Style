package shop.checkout.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String CATALOG_QUEUE = "catalog_queue";
    public static final String HISTORY_QUEUE = "history_queue";
    public static final String EXCHANGE = "message_exchange";
    public static final String CATALOG_ROUTING_KEY = "catalog_routingKey";
    public static final String HISTORY_ROUTING_KEY = "history_routingKey";

    @Bean
    public Queue catalogQueue() {
        return new Queue(CATALOG_QUEUE);
    }

    @Bean
    public Queue historyQueue() {
        return new Queue(HISTORY_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding catalogBinding(Queue catalogQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(catalogQueue)
                .to(exchange)
                .with(CATALOG_ROUTING_KEY);
    }

    @Bean
    public Binding historyBinding(Queue historyQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(historyQueue)
                .to(exchange)
                .with(HISTORY_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
