package com.customservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/customerInfo")
    public String displayBalance() {
        String balanceServiceUrl = "http://ACCOUNTSERVICE/balance";
        String loanServiceUrl = "http://LOANSERVICE/loan";
        return "Your account have : " + restTemplate.getForObject(balanceServiceUrl, String.class) + " " +
                "and you have a loan : " + restTemplate.getForObject(loanServiceUrl, String.class);
    }
}
