package com.cyber009.sb3m.organization.front.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.cyber009.sb3m.common.constant.RabbitMQConstant;
import com.cyber009.sb3m.common.dto.BaseMessage;
import com.cyber009.sb3m.common.dto.OfficeMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSenderService {

    private final RabbitTemplate rabbitTemplate;

    // Send simple string message wrapped in BaseMessage
    public void sendMessage(String message) {
        try {
            BaseMessage<String> baseMessage = BaseMessage.create("SIMPLE_MESSAGE", message);
            rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_NAME, RabbitMQConstant.ROUTING_KEY, baseMessage);
            log.info("📤 Simple message sent successfully: {}", message);
        } catch (Exception e) {
            log.error("❌ Failed to send message: {}", message, e);
            throw new RuntimeException("Failed to send message", e);
        }
    }

    // Send office data as structured JSON
    public void sendOfficeData(String officeName, String location) {
        try {
            OfficeMessage officeMessage = OfficeMessage.createOfficeData(officeName, location);
            BaseMessage<OfficeMessage> baseMessage = BaseMessage.create("OFFICE_DATA", officeMessage);
            
            rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_NAME, RabbitMQConstant.ROUTING_KEY, baseMessage);
            log.info("📤 Office data sent successfully: {} - {}", officeName, location);
        } catch (Exception e) {
            log.error("❌ Failed to send office data: {} - {}", officeName, location, e);
            throw new RuntimeException("Failed to send office data", e);
        }
    }

    // Send custom office message with additional data
    public void sendCustomOfficeMessage(String officeName, String location, String additionalData) {
        try {
            OfficeMessage officeMessage = OfficeMessage.builder()
                    .officeName(officeName)
                    .location(location)
                    .messageType("CUSTOM_OFFICE_DATA")
                    .additionalData(additionalData)
                    .build();
            
            BaseMessage<OfficeMessage> baseMessage = BaseMessage.create("CUSTOM_OFFICE_DATA", officeMessage);
            rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_NAME, RabbitMQConstant.ROUTING_KEY, baseMessage);
            log.info("📤 Custom office message sent successfully: {} - {} - {}", officeName, location, additionalData);
        } catch (Exception e) {
            log.error("❌ Failed to send custom office message: {} - {} - {}", officeName, location, additionalData, e);
            throw new RuntimeException("Failed to send custom office message", e);
        }
    }
}
