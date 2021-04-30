package com.studentmng.usermanagement.repository;


import com.studentmng.usermanagement.model.studentModel;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<studentModel,String>{
}
