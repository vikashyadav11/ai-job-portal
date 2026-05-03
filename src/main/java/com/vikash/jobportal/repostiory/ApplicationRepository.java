package com.vikash.jobportal.repostiory;

import com.vikash.jobportal.entity.Application;
import com.vikash.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByJobIdAndApplicantEmail(Long jobId, String applicantEmail);
}