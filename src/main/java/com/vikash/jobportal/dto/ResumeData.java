package com.vikash.jobportal.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResumeData {

    private String name;
    private String email;
    private List<String> skills;
    private String experience;
}