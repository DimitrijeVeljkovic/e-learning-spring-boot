package com.dveljkovic.elearning.helpers;

public class RatingPayload {
    private int userId;
    private int rating;

    RatingPayload() {

    }

    public RatingPayload(int userId, int rating) {
        this.userId = userId;
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
