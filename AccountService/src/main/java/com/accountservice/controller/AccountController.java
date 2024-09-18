package com.accountservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {
    @GetMapping("/balance")
    public Mono<String> balance() {
        return Mono.just("100$");
    }
}
