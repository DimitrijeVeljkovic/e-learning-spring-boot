package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.auth.JwtTokenProvider;
import com.dveljkovic.elearning.helpers.MessageResponse;
import com.dveljkovic.elearning.helpers.NotePayload;
import com.dveljkovic.elearning.helpers.NoteResponse;
import com.dveljkovic.elearning.service.NoteService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.POST})
public class NoteRestController {
    private NoteService noteService;

    @Autowired
    public NoteRestController(NoteService ns) {
        noteService = ns;
    }

    @PostMapping("")
    public NoteResponse addNoteForUser(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "userId") int userId,
            @RequestParam(name = "courseId") int courseId,
            @RequestBody NotePayload note
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return noteService.addNoteForUser(userId, courseId, note);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }

    @DeleteMapping("")
    public MessageResponse deleteNote(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "noteId") int noteId
    ) throws AuthenticationException {
        if (JwtTokenProvider.isTokenValid(token)) {
            return noteService.deleteNote(noteId);
        }

        throw new AuthenticationException("Auth failed! Token required!");
    }
}
