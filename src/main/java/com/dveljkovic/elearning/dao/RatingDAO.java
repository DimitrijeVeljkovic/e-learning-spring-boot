package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.helpers.RatingPayload;

public interface RatingDAO {
    Rating postRating(int courseId, RatingPayload rating);

    Rating getUserRatingForCourse(int courseId, int userId);
}
