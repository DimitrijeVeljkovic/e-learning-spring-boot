package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.UserDAO;
import com.dveljkovic.elearning.entity.Bookmark;
import com.dveljkovic.elearning.entity.InProgress;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.LoginPayload;
import com.dveljkovic.elearning.helpers.LoginResponse;
import com.dveljkovic.elearning.helpers.SignupResponse;
import jakarta.transaction.Transactional;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImplementation(UserDAO ud) {
        userDAO = ud;
    }

    @Transactional
    @Override
    public SignupResponse createUser(User user) {
        return userDAO.createUser(user);
    }

    @Override
    public LoginResponse findUser(LoginPayload login) throws AuthenticationException {
        return userDAO.findUser(login);
    }

    @Override
    public List<Bookmark> getAllBookmarks(int userId) {
        return userDAO.getAllBookmarks(userId);
    }

    @Override
    public List<InProgress> getAllInProgress(int userId) {
        return userDAO.getAllInProgress(userId);
    }
}
