package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.SignupResponse;

public interface UserDAO {
    SignupResponse createUser(User user);
}
