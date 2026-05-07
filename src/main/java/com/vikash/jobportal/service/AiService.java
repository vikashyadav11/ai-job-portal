package com.vikash.jobportal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Service
public class AiService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String URL = "https://api.openai.com/v1/chat/completions";

    public String parseResume(String resumeText) {

        String prompt = """
                Extract the following from this resume and return ONLY valid JSON:

                {
                  "name": "",
                  "email": "",
                  "skills": [],
                  "experience": ""
                }

                Resume:
                """ + resumeText;

        RestTemplate restTemplate = new RestTemplate();

        // Message
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        // Request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4.1-mini");
        requestBody.put("messages", List.of(message));
        requestBody.put("temperature", 0.2);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        String response = restTemplate.postForObject(
                URL,
                entity,
                String.class
        );

        try {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response);

            String content = root
                    .get("choices")
                    .get(0)
                    .get("message")
                    .get("content")
                    .asText();

            // remove ```json and ```
            content = content.replace("```json", "")
                    .replace("```", "")
                    .trim();

            return content;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response");
        }
    }

    public String scoreResume(String resumeText) {

        String prompt = """
            Analyze this resume.

            Return ONLY valid JSON in this format:

            {
              "resumeScore": 0,
              "strengths": [],
              "weaknesses": [],
              "suggestions": []
            }

            Resume:
            """ + resumeText;

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4.1-mini");
        requestBody.put("messages", List.of(message));
        requestBody.put("temperature", 0.3);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        String response = restTemplate.postForObject(
                URL,
                entity,
                String.class
        );

        try {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response);

            String content = root
                    .get("choices")
                    .get(0)
                    .get("message")
                    .get("content")
                    .asText();

            content = content.replace("```json", "")
                    .replace("```", "")
                    .trim();

            return content;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to parse resume score response"
            );
        }
    }
}