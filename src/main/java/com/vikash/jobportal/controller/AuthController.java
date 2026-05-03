package com.vikash.jobportal.controller;

import com.vikash.jobportal.dto.LoginRequest;
import com.vikash.jobportal.dto.RegisterRequest;
import com.vikash.jobportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    // Public
    @GetMapping("/test")
    public String publicTest() {
        return "Public API";
    }

}