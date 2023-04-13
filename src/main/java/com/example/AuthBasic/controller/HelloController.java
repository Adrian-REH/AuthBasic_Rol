package com.example.AuthBasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class HelloController {


    @GetMapping("/hola")
    public String hello(){
        return "Hello World USER";
    }

    @GetMapping("/world")
    public String helloWorld(){
        return "Hello World ADMIN";
    }
}
