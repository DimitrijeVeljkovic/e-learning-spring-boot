package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.CourseDAO;
import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
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
    public Rating postRating(int courseId, RatingPayload rating) {
        return courseDAO.postRating(courseId, rating);
    }

    @Override
    public Rating getUserRatingForCourse(int courseId, Long userId) {
        return courseDAO.getUserRatingForCourse(courseId, userId);
    }

    @Override
    public List<Bookmark> getAllBookmarks(int userId) {
        return courseDAO.getAllBookmarks(userId);
    }

    @Override
    public List<InProgress> getAllInProgress(int userId) {
        return courseDAO.getAllInProgress(userId);
    }

    @Override
    public List<Completed> getAllCompleted(int userId) {
        return courseDAO.getAllCompleted(userId);
    }

    @Transactional
    @Override
    public MessageResponse startCourse(int userId, StartBookmarkPayload p) throws Exception {
        return courseDAO.startCourse(userId, p);
    }

    @Transactional
    @Override
    public MessageResponse bookmarkCourse(int userId, StartBookmarkPayload p) {
        return courseDAO.bookmarkCourse(userId, p);
    }

    @Transactional
    @Override
    public MessageResponse submitTest(int userId, int courseId, List<QuestionAnswer> body) throws Exception {
        return courseDAO.submitTest(userId, courseId, body);
    }
}
