package com.noellimx.web.service;

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
}
