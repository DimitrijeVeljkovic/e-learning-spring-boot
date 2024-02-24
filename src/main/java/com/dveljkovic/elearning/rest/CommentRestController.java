package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.entity.Comment;
import com.dveljkovic.elearning.helpers.CommentPayload;
import com.dveljkovic.elearning.helpers.MessageResponse;
import com.dveljkovic.elearning.service.CommentService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.DELETE})
public class CommentRestController {
    CommentService commentService;

    @Autowired
    public CommentRestController(CommentService cs) {
        commentService = cs;
    }

    @PostMapping("")
    public Comment postComment(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "courseId") int courseId,
            @RequestBody CommentPayload comment
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return commentService.postComment(courseId, comment);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @DeleteMapping("{commentId}")
    public MessageResponse deleteComment(
            @RequestHeader("Authorization") String token,
            @PathVariable int commentId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return this.commentService.deleteComment(commentId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

}
