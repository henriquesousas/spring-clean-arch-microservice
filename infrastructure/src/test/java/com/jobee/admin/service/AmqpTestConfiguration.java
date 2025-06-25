package com.jobee.admin.service;

import com.rabbitmq.client.Channel;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

/**
 * Creates proxy around each class annotated with @{@link org.springframework.amqp.rabbit.annotation.RabbitListener}
 * that can be used to verify incoming messages via {@link org.springframework.amqp.rabbit.test.RabbitListenerTestHarness}.
 */
@Configuration
@RabbitListenerTest(spy = false, capture = true)
public class AmqpTestConfiguration {

    @Bean
    TestRabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new TestRabbitTemplate(connectionFactory);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        final var factory = mock(ConnectionFactory.class);
        final var connection = mock(Connection.class);
        final var channel = mock(Channel.class);

        BDDMockito.willReturn(connection).given(factory).createConnection();
        BDDMockito.willReturn(channel).given(connection).createChannel(Mockito.anyBoolean());
        BDDMockito.given(channel.isOpen()).willReturn(true);

        return factory;
    }

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(final ConnectionFactory connectionFactory) {
        final var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
