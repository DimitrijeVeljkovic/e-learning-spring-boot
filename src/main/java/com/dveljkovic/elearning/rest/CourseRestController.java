package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
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

    @GetMapping("/bookmark")
    public List<Bookmark> getAllBookmarks(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "userId") int userId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.getAllBookmarks(userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @GetMapping("/in-progress")
    public List<InProgress> getAllInProgress(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "userId") int userId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.getAllInProgress(userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @GetMapping("/in-progress/{courseId}")
    public InProgress getSingleInProgress(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "userId") int userId,
            @PathVariable int courseId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.getSingleInProgress(userId, courseId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @GetMapping("/finish")
    public List<Completed> getAllCompleted(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "userId") int userId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.getAllCompleted(userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @PostMapping("/in-progress")
    public MessageResponse startCourse(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "userId") int userId,
            @RequestBody StartBookmarkPayload p) throws Exception {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.startCourse(userId, p);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @PostMapping("/bookmark")
    public MessageResponse bookmarkCourse(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "userId") int userId,
            @RequestBody StartBookmarkPayload p) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.bookmarkCourse(userId, p);
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

    @PostMapping("/submit/{courseId}")
    public MessageResponse submitTest(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "userId") int userId,
            @PathVariable int courseId,
            @RequestBody List<QuestionAnswer> body
    ) throws Exception {
        if (JwtTokenProvider.isTokenValid(token)) {
            return courseService.submitTest(userId, courseId, body);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }
}
