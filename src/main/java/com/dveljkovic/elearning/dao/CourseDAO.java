package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Course;

import java.util.List;

public interface CourseDAO {
    List<Course> findAll();
}
