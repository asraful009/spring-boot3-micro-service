package com.cyber009.sb3m.organization.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/office")
public class OfficeController {

  @GetMapping(value = "")
  public String find() {
    return String.format("%s", LocalDateTime.now());
  }
}
