package com.LoanService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {
    @GetMapping("/loan")
    public String loan() {
        return ("owe 100$");
    }
}
