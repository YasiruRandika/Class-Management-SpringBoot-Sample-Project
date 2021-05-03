package com.studentmng.coursemanagement.controller;

import java.util.List;

import com.studentmng.coursemanagement.exception.CourseCollectionException;
import com.studentmng.coursemanagement.model.CourseModel;
import com.studentmng.coursemanagement.service.CourseServiceImpl;

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
 * @apiNote Course related api 
 * @category REST Controllers
 */

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;
    
    @GetMapping("")
    public ResponseEntity<?> getAllCourses() {
        List<CourseModel> courseList = courseService.getAllCourses();

        if (courseList.size() > 0) {
            return new ResponseEntity<List<CourseModel>>(courseList, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Records Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCourseByID(@PathVariable("id") String id) {
        try {
            CourseModel stu = courseService.getCourseById(id);
            return new ResponseEntity<CourseModel>(stu, HttpStatus.OK);
        } catch (CourseCollectionException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="")
    public ResponseEntity<?> addCourse(@RequestBody CourseModel Course) {
        try {
            CourseModel stu = courseService.addCourse(Course);
            return new ResponseEntity<CourseModel>(stu, HttpStatus.OK);
        } catch (CourseCollectionException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody CourseModel Course) {
        try {
            CourseModel stu = courseService.updateCourse(Course, id);
            return new ResponseEntity<CourseModel>(stu, HttpStatus.OK);
        } catch (CourseCollectionException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
        } catch (CourseCollectionException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
}
