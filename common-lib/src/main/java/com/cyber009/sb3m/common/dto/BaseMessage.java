package com.cyber009.sb3m.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseMessage<T> {
    
    @JsonProperty("messageId")
    private String messageId;
    
    @JsonProperty("messageType")
    private String messageType;
    
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    
    @JsonProperty("source")
    private String source;
    
    @JsonProperty("payload")
    private T payload;
    
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    // Static factory methods
    public static <T> BaseMessage<T> create(String messageType, T payload) {
        return BaseMessage.<T>builder()
                .messageType(messageType)
                .payload(payload)
                .timestamp(LocalDateTime.now())
                .source("organization-front-service")
                .build();
    }
    
    public static <T> BaseMessage<T> create(String messageType, T payload, String source) {
        return BaseMessage.<T>builder()
                .messageType(messageType)
                .payload(payload)
                .timestamp(LocalDateTime.now())
                .source(source)
                .build();
    }
}
