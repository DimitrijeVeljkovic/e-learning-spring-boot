package com.dveljkovic.elearning.dao;

import com.dveljkovic.elearning.entity.Course;
import com.dveljkovic.elearning.helpers.Counts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

    @Override
    public Counts getNumberOfCourses() {
        long courseCount = (long) entityManager.createQuery("SELECT COUNT(c) FROM Course c").getSingleResult();
        long learningPathCount = (long) entityManager.createQuery("SELECT COUNT(lp) FROM LearningPath lp").getSingleResult();
        return new Counts(courseCount, learningPathCount, 0, 0, 0);
    }

    @Override
    public Counts getNumberOfCoursesForUser(Long userId) {
        long courseCount = (long) entityManager.createQuery("SELECT COUNT(c) FROM Course c").getSingleResult();
        long learningPathCount = (long) entityManager.createQuery("SELECT COUNT(lp) FROM LearningPath lp").getSingleResult();
        // toDo: get 3 other counts
        return new Counts(courseCount, learningPathCount, 0, 0, 0);
    }
}
