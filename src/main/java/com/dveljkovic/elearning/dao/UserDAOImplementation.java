package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.Bookmark;
import com.dveljkovic.elearning.entity.Completed;
import com.dveljkovic.elearning.entity.InProgress;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.LoginPayload;
import com.dveljkovic.elearning.helpers.LoginResponse;
import com.dveljkovic.elearning.helpers.SignupResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public List<Bookmark> getAllBookmarks(int userId) {
        TypedQuery<Bookmark> query = entityManager.createQuery("SELECT b FROM Bookmark b WHERE b.user.userId = :userId", Bookmark.class);
        query.setParameter("userId", userId);

        List<Bookmark> bookmarks = query.getResultList();

        return bookmarks;
    }

    @Override
    public List<InProgress> getAllInProgress(int userId) {
        TypedQuery<InProgress> query = entityManager.createQuery("SELECT i FROM InProgress i WHERE i.user.userId = :userId", InProgress.class);
        query.setParameter("userId", userId);

        List<InProgress> inProgressCourses = query.getResultList();

        return inProgressCourses;
    }

    @Override
    public List<Completed> getAllCompleted(int userId) {
        TypedQuery<Completed> query = entityManager.createQuery("SELECT c FROM Completed c WHERE c.user.userId = :userId", Completed.class);
        query.setParameter("userId", userId);

        List<Completed> completedCourses = query.getResultList();

        return completedCourses;
    }
}
