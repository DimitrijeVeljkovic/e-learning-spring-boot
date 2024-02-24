package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.MessageResponse;

public interface CommentDAO {
    Comment postComment(int courseId, CommentPayload comment);

    MessageResponse deleteComment(int commentId);
}
