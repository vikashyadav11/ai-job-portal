package com.vikash.jobportal.service;

import com.vikash.jobportal.dto.MatchResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MatchingService {

    public MatchResponse calculateMatch(
            List<String> resumeSkills,
            String requiredSkills
    ) {

        List<String> jobSkills =
                Arrays.stream(requiredSkills.split(","))
                        .map(String::trim)
                        .toList();

        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String skill : jobSkills) {

            boolean found = resumeSkills.stream()
                    .anyMatch(s -> s.equalsIgnoreCase(skill));

            if (found) {
                matched.add(skill);
            } else {
                missing.add(skill);
            }
        }

        int score =
                (matched.size() * 100) / jobSkills.size();

        return new MatchResponse(
                score,
                matched,
                missing
        );
    }
}