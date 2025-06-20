package com.jobee.admin.service.infrastructure.configuration;

import com.jobee.admin.service.infrastructure.configuration.annotations.ReviewCreatedQueue;
import com.jobee.admin.service.infrastructure.configuration.annotations.ReviewEvents;
import com.jobee.admin.service.infrastructure.configuration.annotations.ReviewUpdatedQueue;
import com.jobee.admin.service.infrastructure.configuration.properties.amqp.QueueProperties;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/*
* This configurations work as Iac (Infrastructure as Code) it will create
* the queues, exchanges and binding automatically on rabbitmq
* */

@Configuration
public class AmqpConfig {

    @Bean
    @ConfigurationProperties("amqp.queues.review-created")
    @ReviewCreatedQueue
    public QueueProperties reviewCreatedQueueProperties() {
        return new QueueProperties();
    }

    @Bean
    @ConfigurationProperties("amqp.queues.review-updated")
    @ReviewUpdatedQueue
    public QueueProperties reviewUpdatedQueueProperties() {
        return new QueueProperties();
    }

    @Bean
    public DirectExchange reviewDlxExchange(@ReviewCreatedQueue QueueProperties props) {
        return new DirectExchange(props.getDeadLetterExchange());
    }

    @Bean
    public Queue reviewCreatedDeadLetterQueue(@ReviewCreatedQueue QueueProperties props) {
        return new Queue(props.getQueue() + ".dlq");
    }

    @Bean
    public Binding reviewCreatedDeadLetterBinding(
            Queue reviewCreatedDeadLetterQueue,
            DirectExchange reviewDlxExchange,
            @ReviewCreatedQueue QueueProperties props
    ) {
        return BindingBuilder
                .bind(reviewCreatedDeadLetterQueue)
                .to(reviewDlxExchange)
                .with(props.getDeadLetterRoutingKey());
    }

    @Configuration
    public class Admin {

        @Bean
        @ReviewEvents
        public Exchange reviewEventsExchange(@ReviewCreatedQueue QueueProperties props) {
            return new DirectExchange(props.getExchange());
        }

        @Bean
        @ReviewCreatedQueue
        public Queue reviewCreatedQueue(@ReviewCreatedQueue QueueProperties props) {
            Map<String, Object> args = new HashMap<>();
            args.put("x-dead-letter-exchange", props.getDeadLetterExchange());
            args.put("x-dead-letter-routing-key", props.getDeadLetterRoutingKey());
            return new Queue(props.getQueue(),true,false, false, args);
        }

        @Bean
        @ReviewUpdatedQueue
        public Queue reviewUpdatedQueue(@ReviewUpdatedQueue QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        @ReviewCreatedQueue
        public Binding reviewCreatedBinding(
                @ReviewEvents DirectExchange exchange,
                @ReviewCreatedQueue Queue queue,
                @ReviewCreatedQueue QueueProperties properties
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(properties.getRoutingKey());
        }

        @Bean
        @ReviewUpdatedQueue
        public Binding reviewUpdatedBinding(
                @ReviewEvents DirectExchange exchange,
                @ReviewUpdatedQueue Queue queue,
                @ReviewUpdatedQueue QueueProperties properties
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(properties.getRoutingKey());
        }
    }
}
