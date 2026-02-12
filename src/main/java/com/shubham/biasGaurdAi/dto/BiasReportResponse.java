package com.shubham.biasGaurdAi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BiasReportResponse {

    private ScoreResponse original;
    private ScoreResponse anonymized;
    private BiasAnalysis bias;

    }

