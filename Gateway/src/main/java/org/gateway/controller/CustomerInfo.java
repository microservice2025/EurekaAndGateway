package org.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class CustomerInfo {
    @Autowired
    private WebClient.Builder webclient;

    @GetMapping("/customerInfo")
    public Mono<String> test() {
        Mono<String> accountResponse = webclient.build().get().uri("lb://ACCOUNTSERVICE/api/v1/balance")
           .retrieve().bodyToMono(String.class);


        Mono<String> loanResponse = webclient.build().get().uri("lb://LOANSERVICE/api/v1/loan")
           .retrieve().bodyToMono(String.class);


        Mono<String> aggregatedResponse = Mono.zip(accountResponse, loanResponse, (response1, response2) -> {

            // Combine the responses as needed
            return "Account Response: " + response1 + " | Loan Response: " + response2;
        });

        return aggregatedResponse;
    }
}
