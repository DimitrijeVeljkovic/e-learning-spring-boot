package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.helpers.Counts;

import java.util.List;

public interface CourseDAO {
    List<Course> findAll();
    Counts getNumberOfCourses();
    Counts getNumberOfCoursesForUser(Long userId);
}
