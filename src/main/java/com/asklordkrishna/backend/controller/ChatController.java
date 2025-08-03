package com.asklordkrishna.backend.controller;

import com.asklordkrishna.backend.model.ChatRequest;
import com.asklordkrishna.backend.model.ChatResponse;
import com.asklordkrishna.backend.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    @Autowired
    private GeminiService geminiService;

    // POST endpoint for asking Krishna a question
    @PostMapping("/ask")
    public ResponseEntity<ChatResponse> askKrishna(
            @RequestBody ChatRequest chatRequest,
            Authentication authentication
    ) {
        if (chatRequest == null || chatRequest.getQuestion() == null || chatRequest.getQuestion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ChatResponse("Question is required", null));
        }
        // Get the logged-in user (email) if needed: authentication.getName();
        String answer = geminiService.getKrishnaResponse(chatRequest.getQuestion());
        return ResponseEntity.ok(new ChatResponse("success", answer));
    }
}
