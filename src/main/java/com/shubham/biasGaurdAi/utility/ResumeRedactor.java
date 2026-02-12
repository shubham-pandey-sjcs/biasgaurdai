package com.shubham.biasGaurdAi.utility;

public class ResumeRedactor {

    public static String redact(String text) {

        if (text == null || text.isBlank()) {
            return text;
        }

        String[] lines = text.split("\\r?\\n");

        // 1️⃣ Redact first non-empty line (assumed name)
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].trim().isEmpty()) {
                lines[i] = "[REDACTED_NAME]";
                break;
            }
        }

        String redacted = String.join("\n", lines);

        // 2️⃣ Email
        redacted = redacted.replaceAll(
                "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
                "[REDACTED_EMAIL]"
        );

        // 3️⃣ Phone (India + generic 10–13 digit)
        redacted = redacted.replaceAll(
                "\\+?\\d{10,13}",
                "[REDACTED_PHONE]"
        );

        // 4️⃣ URLs (LinkedIn, GitHub, portfolio)
        redacted = redacted.replaceAll(
                "https?://\\S+",
                "[REDACTED_URL]"
        );

        return redacted;
    }
}
