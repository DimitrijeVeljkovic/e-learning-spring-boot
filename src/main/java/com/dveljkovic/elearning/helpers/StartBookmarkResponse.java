package com.dveljkovic.elearning.helpers;

public class StartBookmarkResponse {
    private String message;

    public StartBookmarkResponse() {

    }

    public StartBookmarkResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
