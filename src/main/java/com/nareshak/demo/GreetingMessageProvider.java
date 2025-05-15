package com.nareshak.demo;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class GreetingMessageProvider {

    public String getMessage() {
        return "Hello";
    }
}
