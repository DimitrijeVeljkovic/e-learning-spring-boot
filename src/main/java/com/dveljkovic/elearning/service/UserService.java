package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
import org.apache.tomcat.websocket.AuthenticationException;

public interface UserService {
    SignupResponse createUser(User user);

    LoginResponse findUser(LoginPayload login) throws AuthenticationException;

    User getUser(int userId);

    UpdateUserResponse changeUserData(int userId, User user);

    MessageResponse deleteUser(int userId);
}
