package com.noellimx.main.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.stereotype.Component;

@TestConfiguration
@Component
public class TestSecurityConfig {

  @Value("${spring.security.user.password}")
  private String userPassword;

  public String getUserPassword() {
    return userPassword;
  }
}