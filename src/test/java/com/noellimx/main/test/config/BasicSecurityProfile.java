package com.noellimx.main.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class BasicSecurityProfile {

  @Value("${spring.security.user.name}")
  private String username;

  public String getUsername() {
    return username;
  }

  @Value("${spring.security.user.password}")
  private String password;

  public String getPassword() {
    return password;
  }
}