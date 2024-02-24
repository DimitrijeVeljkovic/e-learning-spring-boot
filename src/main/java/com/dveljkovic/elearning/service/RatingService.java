package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.helpers.RatingPayload;

public interface RatingService {
    Rating postRating(int courseId, RatingPayload rating);

    Rating getUserRatingForCourse(int courseId, int userId);
}
