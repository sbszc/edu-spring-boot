package com.sbszc.eduspringboot.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbszc.eduspringboot.error.NotFoundException;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course findByName(String name) {
		Course course = courseRepository.findByName(name);
		if (course == null) {
			throw new NotFoundException(String.format("Course with name:'%s' not found", name));
		}
		return course;
	}

	public Course save(CourseDto courseDto) {
		return courseRepository.save(new Course(courseDto));
	}
}
