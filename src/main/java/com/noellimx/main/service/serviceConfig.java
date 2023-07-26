package com.noellimx.main.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class serviceConfig {

  @Bean()
  public Service airService() {
    return new AirService();
  }
}
