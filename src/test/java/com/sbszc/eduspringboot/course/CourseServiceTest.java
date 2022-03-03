package com.sbszc.eduspringboot.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        Course course = Course.builder()
                .id(1L)
                .name("spring")
                .description("spring course")
                .build();

        Mockito.when(courseRepository.findByName("spring"))
				.thenReturn(course);
    }

    @Test
    @DisplayName("Get course with a valid course name")
    void whenValidCourseName_thenCourseShouldFound() {
        Long courseId = 1L;
        String courseName = "spring";
        String courseDescription = "spring course";

        Course found = courseService.findByName(courseName);
        assertEquals(courseId, found.getId());
        assertEquals(courseName, found.getName());
        assertEquals(courseDescription, found.getDescription());
    }

}
