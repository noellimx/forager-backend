package com.noellimx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.noellimx.web", "com.noellimx.external"})
public class DummyApplication {

  public static void main(String[] args) {
    SpringApplication.run(DummyApplication.class, args);
  }

}
