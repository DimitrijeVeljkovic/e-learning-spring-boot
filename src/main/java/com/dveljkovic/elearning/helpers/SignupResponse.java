package com.dveljkovic.elearning.helpers;

import com.dveljkovic.elearning.entity.User;

public class SignupResponse {
    private String message;
    private User result;

    public SignupResponse() {

    }

    public SignupResponse(String message, User result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
