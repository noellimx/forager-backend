package com.noellimx.dummy.controllers.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

  @Value("${some.name}")
  String some;

  @GetMapping("/")
  public String sayHello() {
    return "Hello at /" + this.some;
  }

  @GetMapping("/world")
  public String sayHelloWorld() {
    return "Hello World";
  }

  @GetMapping("/universe")
  public String sayHelloUniverse() {
    return "Hello Universe : /";
  }
}
