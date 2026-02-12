package com.shubham.biasGaurdAi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resumeDetail")
@Getter
@Setter
public class Resume {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MinIO object keys
    @Column(nullable = false)
    private String originalFileKey;

    private String anonymizedFileKey;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String anonymizedText;

    @Column(columnDefinition = "TEXT")
    private String originalScore;

    @Column(columnDefinition = "TEXT")
    private String anonymizedScore;

    @Column(columnDefinition = "TEXT")
    private String biasReport;

    @Column(columnDefinition = "TEXT")
    private String status;

}
