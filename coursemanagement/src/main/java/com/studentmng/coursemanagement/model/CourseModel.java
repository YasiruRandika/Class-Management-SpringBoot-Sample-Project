package com.studentmng.coursemanagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Courses")
public class CourseModel {
    @Transient
    public static final String SEQUENCE_NAME = "course_sequence";
    
    @Id
    long id;
    String name;
    String description;
    Date createdAt;
    Date modifiedAt;
}
