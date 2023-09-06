package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseDAOImplementation implements CourseDAO {

    private EntityManager entityManager;

    @Autowired
    public CourseDAOImplementation(EntityManager em) {
        entityManager = em;
    }


    @Override
    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course",
                Course.class
        );

        List<Course> courses = query.getResultList();

        return courses;
    }
}
