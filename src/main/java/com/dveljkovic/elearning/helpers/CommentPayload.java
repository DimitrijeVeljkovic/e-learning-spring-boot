package com.dveljkovic.elearning.helpers;

public class CommentPayload {
    private int userId;
    private String comment;

    public CommentPayload() {

    }

    public CommentPayload(int userId, String comment) {
        this.userId = userId;
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
