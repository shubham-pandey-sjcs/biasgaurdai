package com.shubham.biasGaurdAi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScoreResponse {

    private int technical;
    private int experience;
    private int communication;
    private int overall;
}

