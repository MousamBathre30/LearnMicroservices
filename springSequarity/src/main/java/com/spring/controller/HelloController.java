package com.spring.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {

    @GetMapping("/")
    public String Hello(HttpServletRequest request){
        return "Hello from mousam bathre   " + request.getSession().getId();
    }
}
