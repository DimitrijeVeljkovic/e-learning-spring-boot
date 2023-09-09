package com.dveljkovic.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="note_id")
    private int noteId;

    @Column(name="note")
    private String note;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumns({
            @JoinColumn(name="course_id", referencedColumnName="course_id"),
            @JoinColumn(name="user_id", referencedColumnName="user_id")
    })
    @JsonIgnore
    private InProgress inProgress;

    public Note() {

    }

    public Note(int noteId, String note) {
        this.noteId = noteId;
        this.note = note;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public InProgress getInProgress() {
        return inProgress;
    }

    public void setInProgress(InProgress inProgress) {
        this.inProgress = inProgress;
    }
}
