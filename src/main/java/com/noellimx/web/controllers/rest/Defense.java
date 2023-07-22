package com.noellimx.web.controllers.rest;

import com.noellimx.web.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/defense")
public class Defense {

  Service defenseService;

  @Autowired
  public Defense(@Qualifier("navyService") Service defenseService) {
    this.defenseService = defenseService;
  }

  @GetMapping("/")
  public Integer hormat() {
    return this.defenseService.RollCall();
  }

  @GetMapping("")
  public Integer ho() {
    return hormat();
  }
}
