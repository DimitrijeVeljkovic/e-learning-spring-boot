package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.dao.CourseDAO;
import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class CourseRestController {
    private CourseService courseService;

    @Autowired
    public CourseRestController(CourseService cs) {
        courseService = cs;
    }

    @GetMapping("")
    public List<Course> findAll() {
        return courseService.findAll();
    }
}
