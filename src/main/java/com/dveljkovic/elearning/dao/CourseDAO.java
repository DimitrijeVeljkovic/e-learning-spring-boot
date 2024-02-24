package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;

import java.util.List;

public interface CourseDAO {
    List<Course> findAll();
    Counts getNumberOfCourses();
    Counts getNumberOfCoursesForUser(Long userId);

    List<Bookmark> getAllBookmarks(int userId);

    List<InProgress> getAllInProgress(int userId);

    List<Completed> getAllCompleted(int userId);

    MessageResponse startCourse(int userId, StartBookmarkPayload p) throws Exception;

    MessageResponse bookmarkCourse(int userId, StartBookmarkPayload p);

    MessageResponse submitTest(int userId, int courseId, List<QuestionAnswer> body) throws Exception;

    InProgress getSingleInProgress(int userId, int courseId);
}
