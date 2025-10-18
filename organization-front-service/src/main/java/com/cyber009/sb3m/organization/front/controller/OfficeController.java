package com.cyber009.sb3m.organization.front.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber009.sb3m.common.apipath.ApiPath.OfficeApiPath;
import com.cyber009.sb3m.organization.front.service.MessageSenderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = OfficeApiPath.ROOT)
@Slf4j
@RequiredArgsConstructor
public class OfficeController {

  private final MessageSenderService messageSenderService;

  @GetMapping("")
  public String find() {
    return "hi app";
  }

  @PostMapping("/send-message")
  public String sendMessage(@RequestParam String message) {
    try {
      messageSenderService.sendMessage(message);
      return "Message sent successfully: " + message;
    } catch (Exception e) {
      return "Failed to send message: " + e.getMessage();
    }
  }

  @PostMapping("/send-office")
  public String sendOfficeData(@RequestParam String officeName, @RequestParam String location) {
    try {
      messageSenderService.sendOfficeData(officeName, location);
      return "Office data sent successfully: " + officeName + " - " + location;
    } catch (Exception e) {
      return "Failed to send office data: " + e.getMessage();
    }
  }
}
