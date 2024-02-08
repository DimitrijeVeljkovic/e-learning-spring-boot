package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
import com.dveljkovic.elearning.service.UserService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {
        RequestMethod.POST,
        RequestMethod.GET,
        RequestMethod.PUT,
        RequestMethod.DELETE
})
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

    @PutMapping("/{userId}")
    public UpdateUserResponse changeUserData(
            @RequestHeader("Authorization") String token,
            @PathVariable int userId,
            @RequestBody User user) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return userService.changeUserData(userId, user);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @DeleteMapping("/{userId}")
    public MessageResponse deleteUser(
            @RequestHeader("Authorization") String token,
            @PathVariable int userId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return userService.deleteUser(userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }
}
