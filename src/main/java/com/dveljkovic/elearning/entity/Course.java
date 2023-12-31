package com.dveljkovic.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private int courseId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private List<Rating> ratings;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private List<Comment> comments;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JsonIgnore
    private List<LearningPathCourse> lpCourses;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JsonIgnore
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JsonIgnore
    private List<InProgress> inProgressCourses;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JsonIgnore
    private List<Completed> completedCourses;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private List<Section> sections;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private List<Question> questions;

    public Course() {

    }

    public Course(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<LearningPathCourse> getLpCourses() {
        return lpCourses;
    }

    public void setLpCourses(List<LearningPathCourse> lpCourses) {
        this.lpCourses = lpCourses;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public List<InProgress> getInProgressCourses() {
        return inProgressCourses;
    }

    public void setInProgressCourses(List<InProgress> inProgressCourses) {
        this.inProgressCourses = inProgressCourses;
    }

    public List<Completed> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<Completed> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
