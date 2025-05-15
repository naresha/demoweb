package com.nareshak.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingMessageProvider greetingMessageProvider;

    public GreetingController(GreetingMessageProvider greetingMessageProvider) {
        this.greetingMessageProvider = greetingMessageProvider;
    }

    @GetMapping("/")
    public String greet() {
        return greetingMessageProvider.getMessage();
    }
}
