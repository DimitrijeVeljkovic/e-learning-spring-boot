package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
}
