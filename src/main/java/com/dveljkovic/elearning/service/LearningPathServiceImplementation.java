package com.dveljkovic.elearning.service;

import com.dveljkovic.elearning.dao.LearningPathDAO;
import com.dveljkovic.elearning.entity.LearningPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningPathServiceImplementation implements LearningPathService {

    private LearningPathDAO learningPathDAO;

    @Autowired
    public LearningPathServiceImplementation(LearningPathDAO lpd) {
        learningPathDAO = lpd;
    }

    @Override
    public List<LearningPath> findAll() {
        return learningPathDAO.findAll();
    }
}
