package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.helpers.RatingPayload;
import com.dveljkovic.elearning.service.RatingService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class RatingRestController {
    private RatingService ratingService;

    @Autowired
    public RatingRestController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("")
    public Rating postRating(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "courseId") int courseId,
            @RequestBody RatingPayload rating
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return ratingService.postRating(courseId, rating);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @GetMapping("")
    public Rating ratingForUser(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "courseId") int courseId,
            @RequestParam(name = "userId") int userId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return ratingService.getUserRatingForCourse(courseId, userId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }
}
