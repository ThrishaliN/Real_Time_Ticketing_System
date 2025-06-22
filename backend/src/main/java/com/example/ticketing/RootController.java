package com.example.ticketing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String hello() {
        return "Spring Boot Backend";
    }
}
