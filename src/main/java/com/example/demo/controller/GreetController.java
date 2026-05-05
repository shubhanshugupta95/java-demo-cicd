package com.example.demo.controller;

import com.example.demo.service.GreetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GreetController {

    private final GreetService greetService;

    public GreetController(GreetService greetService) {
        this.greetService = greetService;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "DevOps") String name) {
        return greetService.greet(name);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}