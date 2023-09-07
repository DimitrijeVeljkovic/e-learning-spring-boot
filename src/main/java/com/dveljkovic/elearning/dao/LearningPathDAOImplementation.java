package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.LearningPath;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LearningPathDAOImplementation implements LearningPathDAO {

    private EntityManager entityManager;

    @Autowired
    public LearningPathDAOImplementation(EntityManager em) {
        entityManager = em;
    }

    @Override
    public List<LearningPath> findAll() {
        TypedQuery<LearningPath> query = entityManager.createQuery(
                "from LearningPath",
                LearningPath.class
        );

        List<LearningPath> learningPaths = query.getResultList();

        return learningPaths;
    }
}
