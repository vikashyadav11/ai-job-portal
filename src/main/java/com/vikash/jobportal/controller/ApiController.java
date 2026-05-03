package com.vikash.jobportal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/test")
    public String test() {
        return "Any logged-in user";
    }

    @PreAuthorize("hasAnyRole('ADMIN','RECRUITER')")
    @GetMapping("/manage")
    public String manage() {
        return "Admin or Recruiter";
    }
}