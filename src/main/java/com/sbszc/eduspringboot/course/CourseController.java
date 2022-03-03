package com.sbszc.eduspringboot.course;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Value("${hello.message}")
    private String helloMessage;

    @GetMapping("hello")
    public String hello() {
        return helloMessage;
    }

    @GetMapping
    public List<Course> findAll() {
        log.info("GET api/v1/courses");
        return courseService.findAll();
    }

    @GetMapping("{name}")
    public Course findByName(@PathVariable String name) {
        log.info("GET api/v1/courses/{}", name);
        return courseService.findByName(name);
    }

    @PostMapping
    public Course saveCourse(@Valid @RequestBody CourseDto course) {
        log.info("POST api/v1/courses {}", course);
        return courseService.save(course);
    }

}
