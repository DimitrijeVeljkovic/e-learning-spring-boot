package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.Bookmark;
import com.dveljkovic.elearning.entity.Completed;
import com.dveljkovic.elearning.entity.InProgress;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.*;
import com.dveljkovic.elearning.service.UserService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService us) {
        userService = us;
    }

    @PostMapping("/signup")
    public SignupResponse createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public LoginResponse findUser(@RequestBody LoginPayload login) throws AuthenticationException {
        return userService.findUser(login);
    }

    @GetMapping("/{userId}")
    public User getUser(@RequestHeader("Authorization") String token, @PathVariable int userId) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return userService.getUser(userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @GetMapping("/{userId}/bookmarked-courses")
    public List<Bookmark> getAllBookmarks(@RequestHeader("Authorization") String token, @PathVariable int userId) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return userService.getAllBookmarks(userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @GetMapping("/{userId}/in-progress-courses")
    public List<InProgress> getAllInProgress(@RequestHeader("Authorization") String token, @PathVariable int userId) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return userService.getAllInProgress(userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @GetMapping("/{userId}/finished-courses")
    public List<Completed> getAllCompleted(@RequestHeader("Authorization") String token, @PathVariable int userId) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return userService.getAllCompleted(userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @PostMapping("/{userId}/start-course")
    public StartBookmarkResponse startCourse(
            @RequestHeader("Authorization") String token,
            @PathVariable int userId,
            @RequestBody StartBookmarkPayload p) throws Exception {
        if (JwtTokenProvider.isTokenValid(token)) {
            return userService.startCourse(userId, p);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @PostMapping("/{userId}/bookmark-course")
    public StartBookmarkResponse bookmarkCourse(
            @RequestHeader("Authorization") String token,
            @PathVariable int userId,
            @RequestBody StartBookmarkPayload p) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return userService.bookmarkCourse(userId, p);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }
}
