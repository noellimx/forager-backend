package com.noellimx.dummy.controllers.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/")
    public  String sayHello() {
        return "Hello at /";
    }

    @GetMapping("/world")
    public  String sayHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/universe")
    public  String sayHelloUniverse() {
        return "Hello Universe : /";
    }
}
