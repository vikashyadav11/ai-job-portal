package com.vikash.jobportal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikash.jobportal.dto.ApplicationResponse;
import com.vikash.jobportal.dto.MatchResponse;
import com.vikash.jobportal.dto.ResumeData;
import com.vikash.jobportal.entity.Application;
import com.vikash.jobportal.entity.Job;
import com.vikash.jobportal.repository.ApplicationRepository;
import com.vikash.jobportal.repository.JobRepository;
import com.vikash.jobportal.util.PdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AiService aiService;

    @Autowired
    private MatchingService matchingService;

    @Autowired
    private JobRepository jobRepository;

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

    public ApplicationResponse applyWithResume(Long jobId, String email, MultipartFile file) throws Exception {

        if (applicationRepository.existsByJobIdAndApplicantEmail(jobId, email)) {
            throw new RuntimeException("Already applied to this job");
        }

        // 🔥 Save file
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = "uploads/" + fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath));

        String extractedText = PdfUtil.extractText(filePath);

        String aiResponse = aiService.parseResume(extractedText);
        ObjectMapper mapper = new ObjectMapper();

        ResumeData resumeData =
                mapper.readValue(aiResponse, ResumeData.class);

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        MatchResponse matchResponse =
                matchingService.calculateMatch(
                        resumeData.getSkills(),
                        job.getRequiredSkills()
                );

        System.out.println(matchResponse);

        System.out.println(resumeData.getName());
        System.out.println(resumeData.getSkills());


        // Save application
        Application app = new Application();
        app.setJobId(jobId);
        app.setApplicantEmail(email);
        app.setStatus("APPLIED");
        app.setResumePath(filePath);
        app.setMatchScore(matchResponse.getMatchScore());

        applicationRepository.save(app);
        return new ApplicationResponse("Application submitted successfully", resumeData,matchResponse);
    }
}