package com.LoanService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class LoanController {
    @GetMapping("/loan")
    public Mono<String> loan() {
        return Mono.just("owe 100$");
    }
}
