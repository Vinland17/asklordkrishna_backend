package com.asklordkrishna.backend.model;

public class ChatResponse {
    private String status;
    private String answer;

    public ChatResponse() {}

    public ChatResponse(String status, String answer) {
        this.status = status;
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
