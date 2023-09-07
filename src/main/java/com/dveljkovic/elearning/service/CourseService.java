package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.helpers.Counts;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Counts getNumberOfCourses();
    Counts getNumberOfCoursesForUser(Long userId);
}
