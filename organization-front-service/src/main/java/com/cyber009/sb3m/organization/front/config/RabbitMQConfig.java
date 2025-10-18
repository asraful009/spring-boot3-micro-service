package com.cyber009.sb3m.organization.front.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cyber009.sb3m.common.constant.RabbitMQConstant;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue abcQueue() {
        return QueueBuilder.durable(RabbitMQConstant.QUEUE_NAME)
                .build();
    }
}
