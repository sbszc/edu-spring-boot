package com.sbszc.springboottutorial.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(indexes = {
        @Index(
                name = "UK_course_name",
                columnList = "name",
                unique = true)})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course implements Serializable {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "name is required")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "description is required")
    private String description;

    public Course(CourseDto courseDto) {
        this.name = courseDto.getName();
        this.description = courseDto.getDescription();
    }
}
