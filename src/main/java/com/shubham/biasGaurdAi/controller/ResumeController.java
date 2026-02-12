package com.shubham.biasGaurdAi.controller;

import com.shubham.biasGaurdAi.entity.Resume;
import com.shubham.biasGaurdAi.repository.ResumeRepository;
import com.shubham.biasGaurdAi.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;
    private final ResumeRepository resumeRepository;

    public ResumeController(ResumeService resumeService, ResumeRepository resumeRepository) {
        this.resumeService = resumeService;
        this.resumeRepository = resumeRepository;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("File must not be empty");
            }

            Resume resume = resumeService.uploadResume(file);

            return ResponseEntity.ok(resume);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Resume processing failed: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResume(@PathVariable Long id) {
        return ResponseEntity.ok(
                resumeRepository.findById(id).orElseThrow()
        );
    }

}

