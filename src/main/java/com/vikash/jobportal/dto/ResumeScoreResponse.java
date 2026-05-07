package com.vikash.jobportal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResumeScoreResponse {

    private Integer resumeScore;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> suggestions;
}