package com.vikash.jobportal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/only")
    public String recruiterOnly() {
        return "Only RECRUITER";
    }
}