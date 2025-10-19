package com.cyber009.sb3m.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficeMessage {
    
    @JsonProperty("officeName")
    private String officeName;
    
    @JsonProperty("location")
    private String location;
    
    @JsonProperty("messageType")
    private String messageType;
    
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    
    @JsonProperty("additionalData")
    private String additionalData;
    
    // Static factory methods for easy creation
    public static OfficeMessage createOfficeData(String officeName, String location) {
        return OfficeMessage.builder()
                .officeName(officeName)
                .location(location)
                .messageType("OFFICE_DATA")
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    public static OfficeMessage createSimpleMessage(String message) {
        return OfficeMessage.builder()
                .additionalData(message)
                .messageType("SIMPLE_MESSAGE")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
