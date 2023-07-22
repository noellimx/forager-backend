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
  Service defenseService2;

  @Autowired
  public Defense(@Qualifier("navyService") Service defenseService,
      @Qualifier("navyService") Service defenseService2) {
    // See qualified class for bean scope configuration
    this.defenseService = defenseService;
    this.defenseService2 = defenseService2;
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
