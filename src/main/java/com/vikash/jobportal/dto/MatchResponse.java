package com.vikash.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MatchResponse {

    private int matchScore;

    private List<String> matchedSkills;

    private List<String> missingSkills;
}