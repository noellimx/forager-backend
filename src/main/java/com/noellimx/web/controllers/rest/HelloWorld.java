package com.noellimx.web.controllers.rest;


import com.noellimx.external.Venus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

  @Value("${some.name}")
  String some;

  @Autowired
  Venus venus;

  @GetMapping("/")
  public String sayHello() {
    return "Hello at / " + this.some + venus.some();
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
