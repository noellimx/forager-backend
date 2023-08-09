package com.noellimx.main.controllers.rest;


import com.noellimx.external.Venus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {


  @Autowired
  Venus venus;

  @GetMapping("/")
  public String sayHello() {
    return "Hello at / " + venus.some() + "medalmedalmedal";
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
