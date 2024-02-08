package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.LearningPathDAO;
import com.dveljkovic.elearning.dao.NoteDAO;
import com.dveljkovic.elearning.helpers.MessageResponse;
import com.dveljkovic.elearning.helpers.NotePayload;
import com.dveljkovic.elearning.helpers.NoteResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImplementation implements NoteService {

    private NoteDAO noteDAO;

    @Autowired
    public NoteServiceImplementation(NoteDAO nd) {
        noteDAO = nd;
    }

    @Transactional
    @Override
    public NoteResponse addNoteForUser(int userId, int courseId, NotePayload note) {
        return noteDAO.addNoteForUser(userId, courseId, note);
    }

    @Transactional
    @Override
    public MessageResponse deleteNote(int noteId) {
        return noteDAO.deleteNote(noteId);
    }
}
