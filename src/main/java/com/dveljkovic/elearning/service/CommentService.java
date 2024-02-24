package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.MessageResponse;

public interface CommentService {
    Comment postComment(int courseId, CommentPayload comment);

    MessageResponse deleteComment(int commentId);
}
