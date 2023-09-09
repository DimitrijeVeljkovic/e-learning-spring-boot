package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.entity.Rating;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.Counts;
import com.dveljkovic.elearning.helpers.RatingPayload;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImplementation implements CourseDAO {

    private EntityManager entityManager;

    @Autowired
    public CourseDAOImplementation(EntityManager em) {
        entityManager = em;
    }


    @Override
    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager.createQuery("from Course", Course.class);

        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Counts getNumberOfCourses() {
        long courseCount = (long) entityManager.createQuery("SELECT COUNT(c) FROM Course c").getSingleResult();
        long learningPathCount = (long) entityManager.createQuery("SELECT COUNT(lp) FROM LearningPath lp").getSingleResult();
        return new Counts(courseCount, learningPathCount, 0, 0, 0);
    }

    @Override
    public Counts getNumberOfCoursesForUser(Long userId) {
        long courseCount = (long) entityManager.createQuery("SELECT COUNT(*) FROM Course")
                .getSingleResult();
        long learningPathCount = (long) entityManager.createQuery("SELECT COUNT(*) FROM LearningPath")
                .getSingleResult();
        long inProgressCount = (long) entityManager.createQuery("SELECT COUNT(*) FROM InProgress WHERE user.userId = :userId")
                .setParameter("userId", userId)
                .getSingleResult();
        long bookmarkCount = (long) entityManager.createQuery("SELECT COUNT(*) FROM Bookmark WHERE user.userId = :userId")
                .setParameter("userId", userId)
                .getSingleResult();
        long completeCount = (long) entityManager.createQuery("SELECT COUNT(*) FROM Completed WHERE user.userId = :userId")
                .setParameter("userId", userId)
                .getSingleResult();
        return new Counts(courseCount, learningPathCount, inProgressCount, bookmarkCount, completeCount);
    }

    @Override
    public Comment postComment(int courseId, CommentPayload comment) {
        Query query = entityManager.createNativeQuery("INSERT INTO comment (user_id, course_id, comment) VALUES (:userId, :courseId, :comment)");
        query.setParameter("userId", comment.getUserId());
        query.setParameter("courseId", courseId);
        query.setParameter("comment", comment.getComment());
        query.executeUpdate();

        User u = entityManager.find(User.class, comment.getUserId());
        Comment c = new Comment(comment.getComment());
        c.setUser(u);

        return c;
    }

    @Override
    public Rating postRating(int courseId, RatingPayload rating) {
        Query query = entityManager.createNativeQuery("INSERT INTO rating (user_id, course_id, rating) VALUES (:userId, :courseId, :rating)");
        query.setParameter("userId", rating.getUserId());
        query.setParameter("courseId", courseId);
        query.setParameter("rating", rating.getRating());
        query.executeUpdate();

        Rating r = new Rating(rating.getRating());

        return r;
    }
}
