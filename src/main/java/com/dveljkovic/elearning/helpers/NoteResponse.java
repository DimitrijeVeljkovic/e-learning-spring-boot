package com.dveljkovic.elearning.helpers;

import com.dveljkovic.elearning.entity.Note;

public class NoteResponse {
    private String message;
    private Note note;

    public NoteResponse() {

    }

    public NoteResponse(String message, Note note) {
        this.message = message;
        this.note = note;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
