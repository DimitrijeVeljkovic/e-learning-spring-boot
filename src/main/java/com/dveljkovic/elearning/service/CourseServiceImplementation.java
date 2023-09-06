package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.CourseDAO;
import com.dveljkovic.elearning.entity.Course;
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
}
