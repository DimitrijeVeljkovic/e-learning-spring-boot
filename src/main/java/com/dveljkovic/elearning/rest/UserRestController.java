package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.LoginPayload;
import com.dveljkovic.elearning.helpers.LoginResponse;
import com.dveljkovic.elearning.helpers.SignupResponse;
import com.dveljkovic.elearning.service.UserService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
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
}
