package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
import org.apache.tomcat.websocket.AuthenticationException;

public interface UserDAO {
    SignupResponse createUser(UserDataPayload user, String verificationCode);

    LoginResponse findUser(LoginPayload login) throws AuthenticationException;

    User getUser(int userId);

    UpdateUserResponse changeUserData(int userId, UserDataPayload user);

    MessageResponse deleteUser(int userId);

    MessageResponse verifyUser(VerifyPayload vp) throws Exception;
}
