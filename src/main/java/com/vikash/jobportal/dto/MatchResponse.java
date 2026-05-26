package com.vikash.jobportal.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchResponse {

    private int matchScore;

    private List<String> matchedSkills;

    private List<String> missingSkills;
}