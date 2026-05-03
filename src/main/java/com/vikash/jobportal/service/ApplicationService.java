package com.vikash.jobportal.service;

import com.vikash.jobportal.entity.Application;
import com.vikash.jobportal.repostiory.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application apply(Long jobId, String email) {
        if (applicationRepository.existsByJobIdAndApplicantEmail(jobId, email)) {
            throw new RuntimeException("Already applied to this job");
        }

        Application app = new Application();
        app.setJobId(jobId);
        app.setApplicantEmail(email);
        app.setStatus("APPLIED");

        return applicationRepository.save(app);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application applyWithResume(Long jobId, String email, MultipartFile file) throws IOException {

        if (applicationRepository.existsByJobIdAndApplicantEmail(jobId, email)) {
            throw new RuntimeException("Already applied to this job");
        }

        // 🔥 Save file
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = "uploads/" + fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Save application
        Application app = new Application();
        app.setJobId(jobId);
        app.setApplicantEmail(email);
        app.setStatus("APPLIED");
        app.setResumePath(filePath);

        return applicationRepository.save(app);
    }
}