package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;

import java.util.List;

public interface CourseDAO {
    List<Course> findAll();
    Counts getNumberOfCourses();
    Counts getNumberOfCoursesForUser(Long userId);

    Comment postComment(int courseId, CommentPayload comment);

    Rating postRating(int courseId, RatingPayload rating);

    Rating getUserRatingForCourse(int courseId, Long userId);

    List<Bookmark> getAllBookmarks(int userId);

    List<InProgress> getAllInProgress(int userId);

    List<Completed> getAllCompleted(int userId);

    MessageResponse startCourse(int userId, StartBookmarkPayload p) throws Exception;

    MessageResponse bookmarkCourse(int userId, StartBookmarkPayload p);

    MessageResponse submitTest(int userId, int courseId, List<QuestionAnswer> body) throws Exception;
}
