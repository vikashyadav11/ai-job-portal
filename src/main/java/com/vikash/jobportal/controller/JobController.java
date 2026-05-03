package com.vikash.jobportal.controller;

import com.vikash.jobportal.dto.JobRequest;
import com.vikash.jobportal.entity.Job;
import com.vikash.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // 🔹 POST job (Recruiter only)
    @PreAuthorize("hasRole('RECRUITER')")
    @PostMapping
    public Job createJob(@RequestBody JobRequest request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return jobService.createJob(request, email);
    }

    // 🔹 GET all jobs (any logged-in user)
    @GetMapping
    public List<Job> getJobs() {
        return jobService.getAllJobs();
    }
}