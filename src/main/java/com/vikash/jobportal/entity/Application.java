package com.vikash.jobportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private String applicantEmail;

    private String status; // APPLIED, SHORTLISTED, REJECTED

    private String resumePath;
    private Integer matchScore; // 0 to 100
}