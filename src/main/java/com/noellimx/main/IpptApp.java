package com.noellimx.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.noellimx.main",
    "com.noellimx.external"})
public class IpptApp {

  public static void main(String[] args) {
    SpringApplication.run(IpptApp.class, args);
  }


  @Bean
  public CommandLineRunner clr1() {
    return runner -> {
      System.out.println("clr1: Beans should be loaded");
    };
  }

  @Bean
  public CommandLineRunner clr2() {
    return runner -> {
      System.out.println("clr2: Beans should be loaded");
    };
  }
}
