package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.SignupResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
