package com.vikash.jobportal.service;

import com.vikash.jobportal.entity.Application;
import com.vikash.jobportal.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final ApplicationRepository applicationRepository;

    public List<Application> getApplicants(Long jobId) {

        List<Application> applications =
                applicationRepository.findByJobId(jobId);

        // sort by highest match score
        applications.sort(
                Comparator.comparing(
                        Application::getMatchScore
                ).reversed()
        );

        return applications;
    }

    public Application shortlistCandidate(Long applicationId) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus("SHORTLISTED");

        return applicationRepository.save(application);
    }

    public Application rejectCandidate(Long applicationId) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus("REJECTED");

        return applicationRepository.save(application);
    }
}