package com.asklordkrishna.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GeminiService {

    private static final Logger logger = LoggerFactory.getLogger(GeminiService.class);

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String SYSTEM_PROMPT =
            "You are Lord Krishna from the Bhagavad Gita. " +
                    "Answer each question with wisdom based on Gita philosophy and Hindu life values. " +
                    "Respond in one concise paragraph (no more than 120 words). " +
                    "Do not ask follow-up questions. " +
                    "If the question is inappropriate, gently redirect with compassion.";

    public String getKrishnaResponse(String userQuestion) {
        try {
            logger.info("=== GEMINI API DEBUG START ===");
            logger.info("Processing question: {}", userQuestion);
            logger.info("API Key (first 10 chars): {}...", geminiApiKey.substring(0, Math.min(10, geminiApiKey.length())));
            logger.info("API URL: {}", geminiApiUrl);

            String prompt = SYSTEM_PROMPT + "\n\nUser's question: " + userQuestion;
            ObjectMapper mapper = new ObjectMapper();

            // Create request body
            String requestBody = "{\n" +
                    "  \"contents\": [{\n" +
                    "    \"parts\": [{\n" +
                    "      \"text\": " + mapper.writeValueAsString(prompt) + "\n" +
                    "    }]\n" +
                    "  }]\n" +
                    "}";

            logger.info("Request body: {}", requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-goog-api-key", geminiApiKey);

            logger.info("Headers: Content-Type={}, x-goog-api-key={}...",
                    headers.getContentType(), geminiApiKey.substring(0, Math.min(10, geminiApiKey.length())));

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            logger.info("Making request to Gemini API...");

            ResponseEntity<String> response = restTemplate.exchange(
                    geminiApiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            logger.info("Response status: {}", response.getStatusCode());
            logger.info("Response body: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode root = mapper.readTree(response.getBody());
                JsonNode candidates = root.path("candidates");

                if (candidates.isArray() && candidates.size() > 0) {
                    String answer = candidates.get(0).path("content").path("parts").get(0).path("text").asText();
                    logger.info("Successfully extracted answer: {}", answer);
                    logger.info("=== GEMINI API DEBUG END ===");
                    return answer.trim();
                } else {
                    logger.warn("No candidates found in response");
                    return "I apologize, but I cannot provide guidance at this moment. Please try asking your question differently.";
                }
            } else {
                logger.error("API request failed with status: {}", response.getStatusCode());
                return "I am experiencing difficulties connecting to divine wisdom. Please try again.";
            }

        } catch (Exception e) {
            logger.error("=== GEMINI API ERROR ===");
            logger.error("Exception type: {}", e.getClass().getSimpleName());
            logger.error("Exception message: {}", e.getMessage());
            logger.error("Full stack trace: ", e);
            logger.error("=== GEMINI API ERROR END ===");
            return "There was an error reaching Lord Krishna. Please try again.";
        }
    }
}
