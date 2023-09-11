package com.dveljkovic.elearning.helpers;

public class NoteResponse {
    private String message;
    private String note;

    public NoteResponse() {

    }

    public NoteResponse(String message, String note) {
        this.message = message;
        this.note = note;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
