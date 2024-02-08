package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
import org.apache.tomcat.websocket.AuthenticationException;

public interface UserDAO {
    SignupResponse createUser(User user);

    LoginResponse findUser(LoginPayload login) throws AuthenticationException;

    User getUser(int userId);

    UpdateUserResponse changeUserData(int userId, User user);

    MessageResponse deleteUser(int userId);
}
