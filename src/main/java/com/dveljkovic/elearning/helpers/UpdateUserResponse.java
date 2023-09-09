package com.dveljkovic.elearning.helpers;

import com.dveljkovic.elearning.entity.User;

public class UpdateUserResponse {
    private String message;
    private User user;

    public UpdateUserResponse() {

    }

    public UpdateUserResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
