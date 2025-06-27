package com.hexaware.maverickBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to Maverick Bank!";
    }

    @GetMapping("/customer")
    public String welcomeCustomer() {
        return "Welcome, Customer!";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "You have been successfully logged out.";
    }
}
