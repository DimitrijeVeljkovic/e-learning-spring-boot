package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Bookmark;
import com.dveljkovic.elearning.entity.Completed;
import com.dveljkovic.elearning.entity.InProgress;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.LoginPayload;
import com.dveljkovic.elearning.helpers.LoginResponse;
import com.dveljkovic.elearning.helpers.SignupResponse;
import org.apache.tomcat.websocket.AuthenticationException;

import java.util.List;

public interface UserDAO {
    SignupResponse createUser(User user);

    LoginResponse findUser(LoginPayload login) throws AuthenticationException;

    List<Bookmark> getAllBookmarks(int userId);

    List<InProgress> getAllInProgress(int userId);

    List<Completed> getAllCompleted(int userId);

    User getUser(int userId);
}
