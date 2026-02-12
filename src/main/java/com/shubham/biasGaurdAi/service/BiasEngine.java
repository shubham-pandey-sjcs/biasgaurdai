package com.shubham.biasGaurdAi.service;

import com.shubham.biasGaurdAi.models.CategoryScore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BiasEngine {

    public Map<String, Object> analyze(CategoryScore original,
                                       CategoryScore anonymized) {

        Map<String, Object> result = new HashMap<>();

        int brandDelta =
                original.getBrand_weight() - anonymized.getBrand_weight();

        int overallDelta =
                original.getOverall() - anonymized.getOverall();

        result.put("brand_delta", brandDelta);
        result.put("overall_delta", overallDelta);

        if (Math.abs(brandDelta) >= 3) {
            result.put("bias_severity", "HIGH");
        } else if (Math.abs(brandDelta) >= 1) {
            result.put("bias_severity", "MEDIUM");
        } else {
            result.put("bias_severity", "LOW");
        }

        return result;
    }
}
