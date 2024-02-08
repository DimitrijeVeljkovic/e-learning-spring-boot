package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Note;
import com.dveljkovic.elearning.helpers.MessageResponse;
import com.dveljkovic.elearning.helpers.NotePayload;
import com.dveljkovic.elearning.helpers.NoteResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        Query insertQuery = entityManager.createNativeQuery("INSERT INTO note (note, course_id, user_id) VALUES (:note, :courseId, :userId)");
        insertQuery.setParameter("note", note.getNewNote());
        insertQuery.setParameter("courseId", courseId);
        insertQuery.setParameter("userId", userId);
        insertQuery.executeUpdate();

        TypedQuery<Note> selectQuery = entityManager.createQuery(
                "SELECT n FROM Note n " +
                "WHERE n.note = :note " +
                "ORDER BY n.noteId DESC " +
                "LIMIT 1",
                Note.class
        );
        selectQuery.setParameter("note", note.getNewNote());
        Note n = selectQuery.getSingleResult();

        return new NoteResponse("Note added successfully!", n);
    }

    @Override
    public MessageResponse deleteNote(int noteId) {
        Note n = entityManager.find(Note.class, noteId);
        entityManager.remove(n);

        return new MessageResponse("Note deleted successfully!");
    }
}
