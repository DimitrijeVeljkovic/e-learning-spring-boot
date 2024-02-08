package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.helpers.MessageResponse;
import com.dveljkovic.elearning.helpers.NotePayload;
import com.dveljkovic.elearning.helpers.NoteResponse;

public interface NoteDAO {
    NoteResponse addNoteForUser(int userId, int courseId, NotePayload note);

    MessageResponse deleteNote(int noteId);
}
