package com.example.reminisce_ticketing.model;

import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private Map<String, List<String>> message;

    public Map<String, List<String>> getMessage() {
        return message;
    }

    public void setMessage(Map<String, List<String>> message) {
        this.message = message;
    }
}