package com.example.secondtrainep;

public class ApiResponse {

        public boolean success;
        private String message;

        // Constructor, getters, setters

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
