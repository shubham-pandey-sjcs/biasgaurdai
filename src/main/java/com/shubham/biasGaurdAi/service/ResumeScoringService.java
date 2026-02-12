package com.shubham.biasGaurdAi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubham.biasGaurdAi.models.CategoryScore;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ResumeScoringService {

    private final ChatClient chatClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public ResumeScoringService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public CategoryScore scoreResume(String text) {

        text = text.substring(0, Math.min(text.length(), 1200));

        String prompt = """
        You are a strict backend recruiter.

        Return ONLY valid JSON.
        No explanation.
        No markdown.
        No comments.

        Format:
        {
          "technical_depth": number,
          "project_complexity": number,
          "education_weight": number,
          "brand_weight": number,
          "communication": number,
          "overall": number
        }

        If unsure return 0 instead of null.

        Resume:
        """ + text;

        String raw = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        int first = raw.indexOf("{");
        int last = raw.lastIndexOf("}");

        if (first == -1 || last == -1) {
            throw new RuntimeException("InvalidJSONfromAI: " + raw);
        }

        raw = raw.substring(first, last + 1);

        try {
            JsonNode node = mapper.readTree(raw);
            return mapper.treeToValue(node, CategoryScore.class);
        } catch (Exception e) {
            throw new RuntimeException("InvalidJSONfromAI Raw: " + raw);
        }
    }
}
