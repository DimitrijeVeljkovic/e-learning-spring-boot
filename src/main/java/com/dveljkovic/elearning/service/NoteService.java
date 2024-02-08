package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.helpers.MessageResponse;
import com.dveljkovic.elearning.helpers.NotePayload;
import com.dveljkovic.elearning.helpers.NoteResponse;

public interface NoteService {
    NoteResponse addNoteForUser(int userId, int courseId, NotePayload note);

    MessageResponse deleteNote(int noteId);
}
