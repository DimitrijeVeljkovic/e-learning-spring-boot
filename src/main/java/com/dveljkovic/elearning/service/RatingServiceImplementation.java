package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.RatingDAO;
import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.helpers.RatingPayload;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImplementation implements RatingService {
    private RatingDAO ratingDAO;

    @Autowired
    public RatingServiceImplementation(RatingDAO rd) {
        ratingDAO = rd;
    }

    @Transactional
    @Override
    public Rating postRating(int courseId, RatingPayload rating) {
        return ratingDAO.postRating(courseId, rating);
    }

    @Override
    public Rating getUserRatingForCourse(int courseId, int userId) {
        return ratingDAO.getUserRatingForCourse(courseId, userId);
    }
}
