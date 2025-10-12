package com.cyber009.sb3m.organization.front.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber009.sb3m.common.apipath.ApiPath.OfficeApiPath;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = OfficeApiPath.ROOT)
@Slf4j
@RequiredArgsConstructor
public class OfficeController {

  @GetMapping("")
  public String find() {
    return "hi app";
  }
}
