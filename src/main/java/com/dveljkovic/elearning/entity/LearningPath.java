package com.dveljkovic.elearning.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="learning_path")
public class LearningPath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="learning_path_id")
    private int learningPathId;

    @Column(name="title")
    private String title;

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "learningPath", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private List<LearningPathCourse> lpCourses;

    public LearningPath() {

    }

    public LearningPath(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public int getLearningPathId() {
        return learningPathId;
    }

    public void setLearningPathId(int learningPathId) {
        this.learningPathId = learningPathId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<LearningPathCourse> getLpCourses() {
        return lpCourses;
    }

    public void setLpCourses(List<LearningPathCourse> lpCourses) {
        this.lpCourses = lpCourses;
    }
}
