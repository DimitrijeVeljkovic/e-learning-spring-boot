package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.LearningPath;

import java.util.List;

public interface LearningPathDAO {
    List<LearningPath> findAll();
}
