package com.sbszc.eduspringboot.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private Course course;

    @BeforeEach
    void setUp() {
        course = Course.builder()
                .id(1L)
                .name("java")
                .description("java description")
                .build();
    }

    @Test
    void saveCourse() throws Exception {
        CourseDto courseDto = CourseDto.builder()
                .name("java")
                .description("java description")
                .build();

        Mockito.when(courseService.save(courseDto))
                .thenReturn(course);

        mockMvc.perform(post("/api/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\":\"java\",\n" +
                                "\t\"description\":\"java description\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchCourse() throws Exception {
        Mockito.when(courseService.findByName(course.getName()))
                .thenReturn(course);

        mockMvc.perform(get("/api/v1/courses/" + course.getName()))
                .andExpect(status().isOk());
    }

}
