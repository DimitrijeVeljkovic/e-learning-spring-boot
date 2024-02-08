package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
import jakarta.persistence.EntityManager;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserDAOImplementation implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImplementation(EntityManager em) {
        entityManager = em;
    }
    @Override
    public SignupResponse createUser(User user) {
        User u = entityManager.merge(user);
        return new SignupResponse("User created successfully!", u);
    }

    @Override
    public LoginResponse findUser(LoginPayload login) throws AuthenticationException {
        String email = login.getEmail();
        String q = "SELECT u FROM User u WHERE u.email = :email";
        User u = entityManager.createQuery(q, User.class)
                .setParameter("email", email)
                .getSingleResult();

        if (u != null && Objects.equals(u.getPassword(), login.getPassword())) {
            String token = JwtTokenProvider.generateToken(u.getEmail(), u.getUserId());

            return new LoginResponse(token, u);
        }

        throw new AuthenticationException("Auth failed! Password incorrect!");
    }

    @Override
    public User getUser(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public UpdateUserResponse changeUserData(int userId, User user) {
        User existingUser = entityManager.find(User.class, userId);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setUserName(user.getUserName());
        existingUser.setBookmarks(existingUser.getBookmarks());
        existingUser.setInProgressCourses(existingUser.getInProgressCourses());
        existingUser.setCompletedCourses(existingUser.getCompletedCourses());
        existingUser.setComments(existingUser.getComments());
        entityManager.merge(existingUser);

        return new UpdateUserResponse("Data changed successfully!", user);
    }

    @Override
    public MessageResponse deleteUser(int userId) {
        User u = entityManager.find(User.class, userId);
        entityManager.remove(u);
        return new MessageResponse("User deleted successfully!");
    }
}
