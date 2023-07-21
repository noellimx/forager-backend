package com.noellimx.dummy.controllers.rest;

import com.noellimx.dummy.service.ArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/army")
public class Army {

  ArmyService armyService;

  @Autowired
  public Army(ArmyService armyService) {
    this.armyService = armyService;
  }

  @GetMapping("/")
  public Integer hormat() {
    return this.armyService.RollCall();
  }

  @GetMapping("")
  public Integer ho() {
    return hormat();
  }
}
