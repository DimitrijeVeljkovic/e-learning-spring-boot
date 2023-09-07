package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.SignupResponse;

public interface UserService {
    SignupResponse createUser(User user);
}
