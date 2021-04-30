package com.studentmng.usermanagement.controller;

import java.util.List;

import com.studentmng.usermanagement.exception.StudentCollectionException;
import com.studentmng.usermanagement.model.studentModel;
import com.studentmng.usermanagement.service.StudentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * @author W.G. Yasiru Randika
 * @version 1.0.0
 * @apiNote Student related api 
 * @category REST Controllers
 */

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    
    @GetMapping("")
    public ResponseEntity<?> getAllStudents() {
        List<studentModel> studentList = studentService.getAllStudents();

        if (studentList.size() > 0) {
            return new ResponseEntity<List<studentModel>>(studentList, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Records Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getStudentByID(@PathVariable("id") String id) {
        try {
            studentModel stu = studentService.getStudentById(id);
            return new ResponseEntity<studentModel>(stu, HttpStatus.OK);
        } catch (StudentCollectionException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="")
    public ResponseEntity<?> addStudent(@RequestBody studentModel student) {
        try {
            studentModel stu = studentService.addStudent(student);
            return new ResponseEntity<studentModel>(stu, HttpStatus.OK);
        } catch (StudentCollectionException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody studentModel student) {
        try {
            studentModel stu = studentService.updateStudent(student, id);
            return new ResponseEntity<studentModel>(stu, HttpStatus.OK);
        } catch (StudentCollectionException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
        } catch (StudentCollectionException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
}
