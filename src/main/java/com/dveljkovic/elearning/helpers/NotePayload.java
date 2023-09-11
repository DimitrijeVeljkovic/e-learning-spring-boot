package com.dveljkovic.elearning.helpers;

public class NotePayload {
    private String newNote;

    public NotePayload() {

    }

    public NotePayload(String newNote) {
        this.newNote = newNote;
    }

    public String getNewNote() {
        return newNote;
    }

    public void setNewNote(String newNote) {
        this.newNote = newNote;
    }
}
