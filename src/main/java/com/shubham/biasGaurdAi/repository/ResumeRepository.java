package com.shubham.biasGaurdAi.repository;

import com.shubham.biasGaurdAi.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}

