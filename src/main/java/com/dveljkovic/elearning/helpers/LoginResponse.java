package com.dveljkovic.elearning.helpers;

import com.dveljkovic.elearning.entity.User;

public class LoginResponse {
    private String token;
    private User result;

    public LoginResponse() {

    }

    public LoginResponse(String token, User result) {
        this.token = token;
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
