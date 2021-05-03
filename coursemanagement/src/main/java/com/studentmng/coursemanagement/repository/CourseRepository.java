package com.studentmng.coursemanagement.repository;
import java.util.List;
import java.util.Optional;

import com.studentmng.coursemanagement.model.CourseModel;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface CourseRepository extends MongoRepository<CourseModel, Long>{
    Optional<List<CourseModel>> findByName(String name);
}
