package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Counts getNumberOfCourses();
    Counts getNumberOfCoursesForUser(Long userId);

    List<Bookmark> getAllBookmarks(int userId);

    List<InProgress> getAllInProgress(int userId);

    List<Completed> getAllCompleted(int userId);

    MessageResponse submitTest(int userId, int courseId, List<QuestionAnswer> body) throws Exception;

    MessageResponse startCourse(int userId, StartBookmarkPayload p) throws Exception;

    MessageResponse bookmarkCourse(int userId, StartBookmarkPayload p);

    InProgress getSingleInProgress(int userId, int courseId);
}
