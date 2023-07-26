package com.noellimx.main.controllers.rest;

import com.noellimx.main.service.Service;
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

  Service airService;

  @Autowired
  public Defense(@Qualifier("navyService") Service navyService1,
      @Qualifier("navyService") Service navyService2,
      @Qualifier("airService") Service airService) {
    // See qualified class for bean scope configuration
    this.defenseService = navyService1;
    this.defenseService2 = navyService2;

    this.airService = airService;
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
