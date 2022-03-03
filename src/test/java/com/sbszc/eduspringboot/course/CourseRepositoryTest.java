package com.sbszc.eduspringboot.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Course course = Course.builder()
                .name("spring")
                .description("spring course")
                .build();

        entityManager.persist(course);
    }

    @Test
    @DisplayName("Get course with a valid course id")
    void whenValidId_thenCourseShouldFound() {
        Long courseId = 1L;
        String courseName = "spring";
        String courseDescription = "spring course";

        Course found = courseRepository.getById(courseId);
        assertEquals(courseId, found.getId());
        assertEquals(courseName, found.getName());
        assertEquals(courseDescription, found.getDescription());
    }

}
