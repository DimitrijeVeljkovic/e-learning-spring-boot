package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.UserDAO;
import com.dveljkovic.elearning.entity.Bookmark;
import com.dveljkovic.elearning.entity.Completed;
import com.dveljkovic.elearning.entity.InProgress;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.*;
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

    @Override
    public List<Completed> getAllCompleted(int userId) {
        return userDAO.getAllCompleted(userId);
    }

    @Override
    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }

    @Transactional
    @Override
    public MessageResponse startCourse(int userId, StartBookmarkPayload p) throws Exception {
        return userDAO.startCourse(userId, p);
    }

    @Transactional
    @Override
    public MessageResponse bookmarkCourse(int userId, StartBookmarkPayload p) {
        return userDAO.bookmarkCourse(userId, p);
    }

    @Transactional
    @Override
    public UpdateUserResponse changeUserData(int userId, User user) {
        return userDAO.changeUserData(userId, user);
    }

    @Transactional
    @Override
    public MessageResponse deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    @Transactional
    @Override
    public NoteResponse addNoteForUser(int userId, int courseId, NotePayload note) {
        return userDAO.addNoteForUser(userId, courseId, note);
    }
}
