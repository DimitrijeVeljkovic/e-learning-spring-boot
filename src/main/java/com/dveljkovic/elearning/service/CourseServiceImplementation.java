package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.CourseDAO;
import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.Counts;
import com.dveljkovic.elearning.helpers.RatingPayload;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {
    private CourseDAO courseDAO;

    @Autowired
    public CourseServiceImplementation(CourseDAO cd) {
        courseDAO = cd;
    }
    @Override
    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    @Override
    public Counts getNumberOfCourses() {
        return courseDAO.getNumberOfCourses();
    }

    @Override
    public Counts getNumberOfCoursesForUser(Long userId) {
        return courseDAO.getNumberOfCoursesForUser(userId);
    }

    @Transactional
    @Override
    public Comment postComment(int courseId, CommentPayload comment) {
        return courseDAO.postComment(courseId, comment);
    }

    @Transactional
    @Override
    public Rating postRating(int courseId, RatingPayload rating) {
        return courseDAO.postRating(courseId, rating);
    }

    @Override
    public Rating getUserRatingForCourse(int courseId, Long userId) {
        return courseDAO.getUserRatingForCourse(courseId, userId);
    }
}
