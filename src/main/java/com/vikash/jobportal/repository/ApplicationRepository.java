package com.vikash.jobportal.repository;

import com.vikash.jobportal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByJobIdAndApplicantEmail(Long jobId, String applicantEmail);
    List<Application> findByJobId(Long jobId);

}