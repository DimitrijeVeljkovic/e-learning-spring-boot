package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.entity.InProgress;
import com.dveljkovic.elearning.entity.Note;
import com.dveljkovic.elearning.entity.User;
import com.dveljkovic.elearning.helpers.MessageResponse;
import com.dveljkovic.elearning.helpers.NotePayload;
import com.dveljkovic.elearning.helpers.NoteResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoteDAOImplementation implements NoteDAO {
    private EntityManager entityManager;

    @Autowired
    public NoteDAOImplementation(EntityManager em) {
        entityManager = em;
    }

    @Override
    public NoteResponse addNoteForUser(int userId, int courseId, NotePayload note) {
        User user = entityManager.find(User.class, userId);
        Course course = entityManager.find(Course.class, courseId);
        InProgress ip = new InProgress(user, course);
        InProgress inProgress = entityManager.find(InProgress.class, ip);
        Note newNote = new Note(note.getNewNote());

        newNote.setInProgress(inProgress);

        Note n = entityManager.merge(newNote);


        return new NoteResponse("Note added successfully!", n);
    }

    @Override
    public MessageResponse deleteNote(int noteId) {
        Note n = entityManager.find(Note.class, noteId);
        entityManager.remove(n);

        return new MessageResponse("Note deleted successfully!");
    }
}
