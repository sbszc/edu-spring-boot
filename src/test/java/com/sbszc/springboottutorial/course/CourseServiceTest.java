package com.sbszc.springboottutorial.course;

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
        String courseName = "spring";
        Course found = courseService.findByName(courseName);
        assertEquals(courseName, found.getName());
    }

}
