package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.UserDAO;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.SignupResponse;
import jakarta.transaction.Transactional;
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
    public SignupResponse createUser(User user) {
        return userDAO.createUser(user);
    }
}
