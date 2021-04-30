package com.studentmng.usermanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.studentmng.usermanagement.exception.StudentCollectionException;
import com.studentmng.usermanagement.model.studentModel;
import com.studentmng.usermanagement.repository.StudentRepository;
import com.thoughtworks.xstream.core.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author W.G. Yasiru Randika
 * @version 1.0.0
 * @see StudentService.java
 * @implNote This implements StudentService interface
 */

 @Service
public class StudentServiceImpl implements StudentService, UserDetailsService{
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<studentModel> getAllStudents() {
        List<studentModel> sList = studentRepository.findAll();
        return sList;
    }

    @Override
    public studentModel getStudentById(String id) throws StudentCollectionException{
        Optional<studentModel> student = studentRepository.findById(id);

        if (student.isPresent()) {
            return student.get();
        } else {
            throw new StudentCollectionException(StudentCollectionException.notFoundException(id));
        }
    }

    @Override
    public studentModel addStudent(studentModel student) throws StudentCollectionException {
        Optional<studentModel>stu = studentRepository.findById(student.getStudentId());

        if (stu.isPresent()) {
            throw new StudentCollectionException(StudentCollectionException.studentAlreadyExists(student.getStudentId()));
        }

        student.setCreatedAt(new Date(System.currentTimeMillis()));
        studentRepository.save(student);
        return student;
    }

    @Override
    public studentModel updateStudent(studentModel student, String studenId) throws StudentCollectionException {
        Optional<studentModel>stu = studentRepository.findById(studenId);

        if (!stu.isPresent()) {
            throw new StudentCollectionException(StudentCollectionException.notFoundException(studenId));
        }

        studentModel studentModel = stu.get();

        studentModel.setName(student.getName() != null ? student.getName() : studentModel.getName());
        studentModel.setEmail(student.getEmail() != null ? student.getEmail() : studentModel.getEmail());
        studentModel.setPhone(student.getPhone() != null ? student.getPhone() : studentModel.getPhone());
        studentModel.setPassword(student.getPassword() != null ? student.getName() : studentModel.getName());
        studentModel.setModifiedAt(new Date(System.currentTimeMillis()));

        studentRepository.save(studentModel);

        return studentModel;
    }

    @Override
    public void deleteStudent(String id) throws StudentCollectionException {
        Optional<studentModel>stu = studentRepository.findById(id);

        if (stu.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentCollectionException(StudentCollectionException.notFoundException(id));
        }
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<studentModel>stu = studentRepository.findById(username);

        if (stu.isPresent()) {
            return new User(username, stu.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException(username);
        }
        
    }
}
