package com.dveljkovic.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="course_from_learning_path")
public class LearningPathCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="learning_path_course_id")
    private int learningPathCourseId;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="learning_path_id")
    @JsonIgnore
    private LearningPath learningPath;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="course_id")
    private Course course;

    public LearningPathCourse() {

    }

    public int getLearningPathCourseId() {
        return learningPathCourseId;
    }

    public void setLearningPathCourseId(int learningPathCourseId) {
        this.learningPathCourseId = learningPathCourseId;
    }

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
