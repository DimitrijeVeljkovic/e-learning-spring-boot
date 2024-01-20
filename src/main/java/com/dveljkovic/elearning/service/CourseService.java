package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.Counts;
import com.dveljkovic.elearning.helpers.RatingPayload;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Counts getNumberOfCourses();
    Counts getNumberOfCoursesForUser(Long userId);

    Comment postComment(int courseId, CommentPayload comment);

    Rating postRating(int courseId, RatingPayload rating);

    Rating getUserRatingForCourse(int courseId, Long userId);
}
