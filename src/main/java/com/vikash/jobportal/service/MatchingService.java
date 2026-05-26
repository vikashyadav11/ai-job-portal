package com.vikash.jobportal.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikash.jobportal.dto.MatchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final AiService aiService;

    private final ObjectMapper objectMapper;

    public MatchResponse calculateMatch(
            List<String> resumeSkills,
            String jobSkills
    ) throws Exception {

        String aiResponse =
                aiService.semanticSkillMatch(
                        resumeSkills,
                        jobSkills
                );

        JsonNode root =
                objectMapper.readTree(aiResponse);

        String content =
                root.get("choices")
                        .get(0)
                        .get("message")
                        .get("content")
                        .asText();

        content = content
                .replace("```json", "")
                .replace("```", "")
                .trim();

        return objectMapper.readValue(
                content,
                MatchResponse.class
        );
    }
}