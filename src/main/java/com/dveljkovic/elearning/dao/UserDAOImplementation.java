package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.*;
import com.dveljkovic.elearning.helpers.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Override
    public User getUser(int userId) {
        return entityManager.find(User.class, userId);
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
