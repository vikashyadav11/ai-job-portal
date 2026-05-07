package com.vikash.jobportal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikash.jobportal.dto.ResumeScoreResponse;
import com.vikash.jobportal.util.PdfUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final AiService aiService;

    public ResumeScoreResponse scoreResume(
            MultipartFile file
    ) throws Exception {

        // Save temporary file
        String fileName =
                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        String filePath = "uploads/" + fileName;

        Files.copy(
                file.getInputStream(),
                Paths.get(filePath)
        );

        // Extract text
        String extractedText =
                PdfUtil.extractText(filePath);

        // AI response
        String aiResponse =
                aiService.scoreResume(extractedText);

        // JSON → DTO
        ObjectMapper mapper =
                new ObjectMapper();

        return mapper.readValue(
                aiResponse,
                ResumeScoreResponse.class
        );
    }
}