package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.Counts;
import com.dveljkovic.elearning.helpers.RatingPayload;
import com.dveljkovic.elearning.service.CourseService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
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

    @PostMapping("/{courseId}/comment")
    public Comment postComment(
            @RequestHeader("Authorization") String token,
            @PathVariable int courseId,
            @RequestBody CommentPayload comment
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.postComment(courseId, comment);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @PostMapping("/{courseId}/rating")
    public Rating postRating(
            @RequestHeader("Authorization") String token,
            @PathVariable int courseId,
            @RequestBody RatingPayload rating
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.postRating(courseId, rating);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @GetMapping("/{courseId}/rating")
    public Rating ratingForUser(
            @RequestHeader("Authorization") String token,
            @PathVariable int courseId,
            @RequestParam(name = "userId") Long userId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.getUserRatingForCourse(courseId, userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }
}
