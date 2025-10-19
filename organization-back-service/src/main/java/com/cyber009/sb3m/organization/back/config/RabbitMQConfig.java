package com.cyber009.sb3m.organization.back.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cyber009.sb3m.common.constant.RabbitMQConstant;

@Configuration
public class RabbitMQConfig {

    // Declare the exchange
    @Bean
    public DirectExchange organizationExchange() {
        return new DirectExchange(RabbitMQConstant.EXCHANGE_NAME);
    }

    // Declare the queue
    @Bean
    public Queue abcQueue() {
        return QueueBuilder.durable(RabbitMQConstant.QUEUE_NAME)
                .build();
    }

    // Bind queue to exchange with routing key
    @Bean
    public Binding abcBinding() {
        return BindingBuilder
                .bind(abcQueue())
                .to(organizationExchange())
                .with(RabbitMQConstant.ROUTING_KEY);
    }
}
