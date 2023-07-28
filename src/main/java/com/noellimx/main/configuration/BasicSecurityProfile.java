package com.noellimx.main.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class BasicSecurityProfile {

  @Value("${spring.security.user.name}")
  private String username;

  @Value("${spring.security.user.password}")
  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}