package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.MessageResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImplementation implements CommentDAO {

    private EntityManager entityManager;

    @Autowired
    public CommentDAOImplementation(EntityManager em) {
        entityManager = em;
    }

    @Override
    public Comment postComment(int courseId, CommentPayload comment) {
        User user = entityManager.find(User.class, comment.getUserId());
        Course course = entityManager.find(Course.class, courseId);
        Comment newComment = new Comment(comment.getComment());

        newComment.setUser(user);
        newComment.setCourse(course);

        return entityManager.merge(newComment);
    }

    @Override
    public MessageResponse deleteComment(int commentId) {
        Comment c = entityManager.find(Comment.class, commentId);
        entityManager.remove(c);

        return new MessageResponse("Comment removed successfully!");
    }
}
