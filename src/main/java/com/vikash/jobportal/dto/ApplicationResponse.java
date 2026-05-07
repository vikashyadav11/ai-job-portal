package com.vikash.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationResponse {

    private String message;
    private ResumeData resumeData;
    private MatchResponse matchResponse;
}