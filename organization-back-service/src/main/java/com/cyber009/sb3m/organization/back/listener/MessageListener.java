package com.cyber009.sb3m.organization.back.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cyber009.sb3m.common.constant.RabbitMQConstant;
import com.cyber009.sb3m.common.dto.BaseMessage;
import com.cyber009.sb3m.common.dto.OfficeMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageListener {
  
  private final ObjectMapper objectMapper;

  @RabbitListener(queues = RabbitMQConstant.QUEUE_NAME)
  public void receiveMessage(BaseMessage<?> baseMessage) {
    log.info("📩 Received BaseMessage: Type={}, Source={}, Timestamp={}", 
             baseMessage.getMessageType(), 
             baseMessage.getSource(), 
             baseMessage.getTimestamp());
    
    // Handle different message types
    switch (baseMessage.getMessageType()) {
      case "SIMPLE_MESSAGE":
        handleSimpleMessage(objectMapper.convertValue(baseMessage.getPayload(), String.class));
        break;
      case "OFFICE_DATA":
        handleOfficeData(objectMapper.convertValue(baseMessage.getPayload(), OfficeMessage.class));
        break;
      case "CUSTOM_OFFICE_DATA":
        handleCustomOfficeData(objectMapper.convertValue(baseMessage.getPayload(), OfficeMessage.class));
        break;
      default:
        log.warn("⚠️ Unknown message type: {}", baseMessage.getMessageType());
        log.info("📩 Raw payload: {}", baseMessage.getPayload());
    }
  }
  
  private void handleSimpleMessage(String message) {
    log.info("📩 Simple message: {}", message);
  }
  
  private void handleOfficeData(OfficeMessage officeMessage) {
    log.info("📩 Office Data - Name: {}, Location: {}, Type: {}", 
             officeMessage.getOfficeName(), 
             officeMessage.getLocation(), 
             officeMessage.getMessageType());
  }
  
  private void handleCustomOfficeData(OfficeMessage officeMessage) {
    log.info("📩 Custom Office Data - Name: {}, Location: {}, Additional: {}, Type: {}", 
             officeMessage.getOfficeName(), 
             officeMessage.getLocation(), 
             officeMessage.getAdditionalData(),
             officeMessage.getMessageType());
  }
}
