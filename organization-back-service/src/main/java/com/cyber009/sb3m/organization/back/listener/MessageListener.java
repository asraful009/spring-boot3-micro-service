package com.cyber009.sb3m.organization.back.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cyber009.sb3m.common.constant.RabbitMQConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {
  @RabbitListener(queues = RabbitMQConstant.QUEUE_NAME)
  public void receiveMessage(String message) {
    System.out.println("📩 Received message: " + message);
  }
}
