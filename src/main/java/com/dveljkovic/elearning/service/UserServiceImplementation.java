package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.UserDAO;
import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
import jakarta.transaction.Transactional;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImplementation(UserDAO ud) {
        userDAO = ud;
    }

    @Transactional
    @Override
    public SignupResponse createUser(UserDataPayload user, String verificationCode) {
        return userDAO.createUser(user, verificationCode);
    }

    @Override
    public LoginResponse findUser(LoginPayload login) throws AuthenticationException {
        return userDAO.findUser(login);
    }

    @Override
    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }

    @Transactional
    @Override
    public UpdateUserResponse changeUserData(int userId, UserDataPayload user) {
        return userDAO.changeUserData(userId, user);
    }

    @Transactional
    @Override
    public MessageResponse deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    @Transactional
    @Override
    public MessageResponse verifyUser(VerifyPayload vp) throws Exception {
        return userDAO.verifyUser(vp);
    }
}
