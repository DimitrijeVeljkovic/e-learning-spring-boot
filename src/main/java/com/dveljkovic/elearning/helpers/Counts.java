package com.dveljkovic.elearning.helpers;

public class Counts {
    private long courseCount;
    private long learningPathCount;
    private long inProgressCount;
    private long bookmarkCount;
    private long completeCount;

    public Counts() {

    }

    public Counts(long courseCount, long learningPathCount, long inProgressCount, long bookmarkCount, long completeCount) {
        this.courseCount = courseCount;
        this.learningPathCount = learningPathCount;
        this.inProgressCount = inProgressCount;
        this.bookmarkCount = bookmarkCount;
        this.completeCount = completeCount;
    }

    public long getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(long courseCount) {
        this.courseCount = courseCount;
    }

    public long getLearningPathCount() {
        return learningPathCount;
    }

    public void setLearningPathCount(long learningPathCount) {
        this.learningPathCount = learningPathCount;
    }

    public long getInProgressCount() {
        return inProgressCount;
    }

    public void setInProgressCount(long inProgressCount) {
        this.inProgressCount = inProgressCount;
    }

    public long getBookmarkCount() {
        return bookmarkCount;
    }

    public void setBookmarkCount(long bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }

    public long getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(long completeCount) {
        this.completeCount = completeCount;
    }
}
