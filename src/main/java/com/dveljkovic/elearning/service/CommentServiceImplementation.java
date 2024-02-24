package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.CommentDAO;
import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements CommentService {

    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImplementation(CommentDAO cd) {
        commentDAO = cd;
    }

    @Transactional
    @Override
    public Comment postComment(int courseId, CommentPayload comment) {
        return commentDAO.postComment(courseId, comment);
    }

    @Transactional
    @Override
    public MessageResponse deleteComment(int commentId) {
        return commentDAO.deleteComment(commentId);
    }
}
