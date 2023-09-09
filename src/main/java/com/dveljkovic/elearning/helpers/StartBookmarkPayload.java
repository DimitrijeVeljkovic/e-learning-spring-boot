package com.dveljkovic.elearning.helpers;

public class StartBookmarkPayload {
    private int courseId;

    public StartBookmarkPayload() {

    }

    public StartBookmarkPayload(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
