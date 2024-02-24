package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.helpers.RatingPayload;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RatingDAOImplementation implements RatingDAO {
    private EntityManager entityManager;

    @Autowired
    public RatingDAOImplementation(EntityManager em) {
        entityManager = em;
    }

    @Override
    public Rating postRating(int courseId, RatingPayload rating) {
        int userRating = getUserRatingForCourse(courseId, rating.getUserId()).getRating();

        if (userRating == 0) {
            Query insertQuery = entityManager.createNativeQuery("INSERT INTO rating (user_id, course_id, rating) VALUES (:userId, :courseId, :rating)");
            insertQuery.setParameter("userId", rating.getUserId());
            insertQuery.setParameter("courseId", courseId);
            insertQuery.setParameter("rating", rating.getRating());
            insertQuery.executeUpdate();

            return new Rating(rating.getRating());
        }

        Query updateQuery = entityManager.createNativeQuery("UPDATE rating SET rating = :rating WHERE course_id = :courseId AND user_id = :userId");
        updateQuery.setParameter("userId", rating.getUserId());
        updateQuery.setParameter("courseId", courseId);
        updateQuery.setParameter("rating", rating.getRating());
        updateQuery.executeUpdate();

        return new Rating(rating.getRating());
    }

    @Override
    public Rating getUserRatingForCourse(int courseId, int userId) {
        Query query = entityManager.createNativeQuery("SELECT rating FROM rating WHERE user_id = :userId AND course_id = :courseId");
        query.setParameter("courseId", courseId);
        query.setParameter("userId", userId);

        Object ratingToReturn;

        try {
            ratingToReturn = query.getSingleResult();
        } catch (NoResultException err) {
            return new Rating(0);
        }

        return new Rating((int) ratingToReturn);
    }
}
