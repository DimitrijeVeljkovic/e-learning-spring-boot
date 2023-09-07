package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.SignupResponse;
import com.dveljkovic.elearning.service.UserService;
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
        System.out.println(user.toString());
        return userService.createUser(user);
    }
}
