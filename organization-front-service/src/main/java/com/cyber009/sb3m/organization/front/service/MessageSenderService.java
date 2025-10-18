package com.cyber009.sb3m.organization.front.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.cyber009.sb3m.common.constant.RabbitMQConstant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSenderService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE_NAME, message);
            log.info("📤 Message sent successfully: {}", message);
        } catch (Exception e) {
            log.error("❌ Failed to send message: {}", message, e);
            throw new RuntimeException("Failed to send message", e);
        }
    }

    public void sendOfficeData(String officeName, String location) {
        String message = String.format("Office: %s, Location: %s", officeName, location);
        sendMessage(message);
    }
}
