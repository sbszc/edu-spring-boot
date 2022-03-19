package com.sbszc.eduspringboot.course;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Slf4j
class CourseRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        Course course1 = Course.builder()
                .name("java")
                .description("java course")
                .build();
        Course persistedCourse1 = testEntityManager.persist(course1);
        log.info(String.format("persistedCourse1 %s", persistedCourse1));

        Course course2 = Course.builder()
                .name("js")
                .description("js course")
                .build();
        Course persistedCourse2 = testEntityManager.persist(course2);
        log.info(String.format("persistedCourse2 %s", persistedCourse2));
    }

    @Test
    @DisplayName("Get courses with a valid course ids")
    void whenValidIds_thenCoursesShouldFound() {
        Long course1Id = 1L;
        String course1Name = "java";
        String course1Description = "java course";
        Course foundCourse1 = courseRepository.getById(course1Id);
        log.info(String.format("foundCourse1 %s", foundCourse1));
        assertEquals(course1Id, foundCourse1.getId());
        assertEquals(course1Name, foundCourse1.getName());
        assertEquals(course1Description, foundCourse1.getDescription());

        Long course2Id = 2L;
        String course2Name = "js";
        String course2Description = "js course";
        Course foundCourse2 = courseRepository.getById(course2Id);
        log.info(String.format("foundCourse2 %s", foundCourse2));
        assertEquals(course2Id, foundCourse2.getId());
        assertEquals(course2Name, foundCourse2.getName());
        assertEquals(course2Description, foundCourse2.getDescription());
    }

}
