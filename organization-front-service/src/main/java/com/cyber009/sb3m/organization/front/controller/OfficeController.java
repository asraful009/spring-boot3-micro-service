package com.cyber009.sb3m.organization.front.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/office")
@Slf4j
@RequiredArgsConstructor
public class OfficeController {

  public String find() {
    return "hi app";
  }
}
