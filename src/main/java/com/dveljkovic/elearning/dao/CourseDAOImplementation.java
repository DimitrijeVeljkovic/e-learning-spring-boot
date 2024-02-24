package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    public Rating postRating(int courseId, RatingPayload rating) {
        int userRating = getUserRatingForCourse(courseId, (long) rating.getUserId()).getRating();

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
    public Rating getUserRatingForCourse(int courseId, Long userId) {
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

    @Override
    public List<Bookmark> getAllBookmarks(int userId) {
        TypedQuery<Bookmark> query = entityManager.createQuery("SELECT b FROM Bookmark b WHERE b.user.userId = :userId", Bookmark.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public List<InProgress> getAllInProgress(int userId) {
        TypedQuery<InProgress> query = entityManager.createQuery("SELECT i FROM InProgress i WHERE i.user.userId = :userId", InProgress.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public List<Completed> getAllCompleted(int userId) {
        TypedQuery<Completed> query = entityManager.createQuery("SELECT c FROM Completed c WHERE c.user.userId = :userId", Completed.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public MessageResponse startCourse(int userId, StartBookmarkPayload p) throws Exception {
        Query query = entityManager.createNativeQuery("INSERT INTO in_progress (user_id, course_id) VALUES (:userId, :courseId)");
        query.setParameter("userId", userId);
        query.setParameter("courseId", p.getCourseId());

        Completed cid = new Completed();
        User user = new User();
        user.setUserId(userId);
        cid.setUser(user);
        Course course = new Course();
        course.setCourseId(p.getCourseId());
        cid.setCourse(course);
        Completed c = entityManager.find(Completed.class, cid);

        if (c == null) {
            query.executeUpdate();
            return new MessageResponse("Course started successfully!");
        }

        throw new Exception("Cannot start course! Already finished!");
    }

    @Override
    public MessageResponse bookmarkCourse(int userId, StartBookmarkPayload p) {
        Query query = entityManager.createNativeQuery("INSERT INTO bookmark (user_id, course_id) VALUES (:userId, :courseId)");
        query.setParameter("userId", userId);
        query.setParameter("courseId", p.getCourseId());
        query.executeUpdate();

        return new MessageResponse("Course bookmarked successfully!");
    }

    @Override
    public MessageResponse submitTest(int userId, int courseId, List<QuestionAnswer> body) throws Exception {
        int correctAnswers = 0;
        TypedQuery<Question> query = entityManager.createQuery("SELECT q FROM Question q WHERE q.course.courseId = :courseId", Question.class);
        query.setParameter("courseId", courseId);
        List<Question> questions = query.getResultList();

        for (Question q: questions) {
            for (QuestionAnswer qa: body) {
                if (q.getQuestionId() == qa.getQuestionId() && q.getAnswer().equals(qa.getAnswer())) {
                    correctAnswers++;
                }
            }
        }

        double percentage = ((double) correctAnswers / questions.size()) * 100;
        if (percentage >= 60) {
            Query insertIntoCompleted = entityManager.createNativeQuery(
                    "INSERT INTO completed (user_id, course_id, date, percentage) VALUES (:userId, :courseId, :date, :percentage)"
            );
            insertIntoCompleted.setParameter("userId", userId);
            insertIntoCompleted.setParameter("courseId", courseId);
            insertIntoCompleted.setParameter("date", new Date().toString());
            insertIntoCompleted.setParameter("percentage", percentage);
            insertIntoCompleted.executeUpdate();

            TypedQuery<InProgress> getInProgress = entityManager.createQuery(
                    "SELECT ip FROM InProgress ip WHERE ip.user.userId = :userId AND ip.course.courseId = :courseId",
                    InProgress.class
            );
            getInProgress.setParameter("userId", userId);
            getInProgress.setParameter("courseId", courseId);
            InProgress inProgressToDelete = getInProgress.getSingleResult();
            entityManager.remove(inProgressToDelete);


            return new MessageResponse("Test passed successfully! Percentage: " + String.format("%.2f", percentage) + "%");
        }

        throw new Exception("Test is not passed! Try again! Percentage: " + String.format("%.2f", percentage) + "%");
    }
}
