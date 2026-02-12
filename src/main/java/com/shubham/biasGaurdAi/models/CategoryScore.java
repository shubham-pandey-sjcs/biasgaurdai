package com.shubham.biasGaurdAi.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryScore {

    private int technical_depth;
    private int project_complexity;
    private int education_weight;
    private int brand_weight;
    private int communication;
    private int overall;

    // getters & setters
}

