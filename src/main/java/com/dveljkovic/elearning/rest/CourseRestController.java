package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.helpers.Counts;
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

    @GetMapping("/count")
    public Counts getNumberOfCourses(@RequestParam(name = "userId", required = false) Long userId) {
        if (userId != null) {
            return courseService.getNumberOfCoursesForUser(userId);
        } else {
            return courseService.getNumberOfCourses();
        }
    }
}
