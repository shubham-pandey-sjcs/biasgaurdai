package com.shubham.biasGaurdAi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubham.biasGaurdAi.entity.Resume;
import com.shubham.biasGaurdAi.models.CategoryScore;
import com.shubham.biasGaurdAi.repository.ResumeRepository;
import com.shubham.biasGaurdAi.utility.ResumeRedactor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeScoringService resumeScoringService;
    private final BiasEngine biasEngine;
    private final ObjectMapper mapper = new ObjectMapper();

    public ResumeService(ResumeRepository resumeRepository,
                         ResumeScoringService resumeScoringService,
                         BiasEngine biasEngine) {

        this.resumeRepository = resumeRepository;
        this.resumeScoringService = resumeScoringService;
        this.biasEngine = biasEngine;
    }

    public Resume uploadResume(MultipartFile file) throws Exception {

        String text = extractText(file);
        text = text.replaceAll("\\s+", " ");
        text = text.substring(0, Math.min(text.length(), 1200));


        String anonymized = ResumeRedactor.redact(text);

        CategoryScore originalScore =
                resumeScoringService.scoreResume(text);

        CategoryScore anonymizedScore =
                resumeScoringService.scoreResume(anonymized);

        var biasReport =
                biasEngine.analyze(originalScore, anonymizedScore);

        Resume resume = new Resume();
        String originalKey = "resumes/original/" + UUID.randomUUID() + ".pdf";
        resume.setOriginalFileKey(originalKey);
        String anonymizedKey = "resumes/anonymized/" + UUID.randomUUID() + ".txt";
        resume.setAnonymizedFileKey(anonymizedKey);
        resume.setAnonymizedText(anonymized);
        resume.setOriginalScore(mapper.writeValueAsString(originalScore));
        resume.setAnonymizedScore(mapper.writeValueAsString(anonymizedScore));
        resume.setBiasReport(mapper.writeValueAsString(biasReport));
        resume.setStatus("COMPLETED");

        return resumeRepository.save(resume);
    }

    private String extractText(MultipartFile file) throws Exception {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setEndPage(2);
            return stripper.getText(document);
        }
    }
}
