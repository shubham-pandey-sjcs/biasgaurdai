package com.shubham.biasGaurdAi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BiasAnalysis {
    private boolean biasDetected;
    private int difference;
    private String impactLevel;
    private int confidence;

}
