package com.noellimx.web.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NavyService implements Service {


  private static int instanceId = 0;
  private int id;

  public NavyService() {
    this.id = instanceId;
    instanceId++;
  }

  public int RollCall() {
    return -2;
  }


  @PostConstruct
  public void message() {
    System.out.println("msg1: Navy Service " + id + " constructed.");
  }

  @PostConstruct
  public void message2() {
    System.out.println("msg2: Navy Service " + id + " constructed.");
  }

}
