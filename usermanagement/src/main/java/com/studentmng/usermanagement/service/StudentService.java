package com.studentmng.usermanagement.service;

import java.util.List;

import com.studentmng.usermanagement.exception.StudentCollectionException;
import com.studentmng.usermanagement.model.studentModel;


/**
 * @author W.G. Yasiru Randika
 * Student Service Interface
 * @version 1.0.0
 */

public interface StudentService {
    /**
     * Get all students in the collection
     * @param
     * @return List<StudentModel>
     */
    public List<studentModel> getAllStudents();

    /**
     * Get a student in the collection by Id
     * @param id
     * @return StudentModel
     * @throws StudentCollectionException
     */
    public studentModel getStudentById(String id) throws StudentCollectionException;

    /**
     * Add student to the collection
     * @param StudentModel
     * @return StudentModel
     * @throws StudentCollectionException
     */
    public studentModel addStudent(studentModel student) throws StudentCollectionException;

    /**
     * Update student in the collection
     * @param StudentModel
     * @return StudentModel
     * @throws StudentCollectionException
     */
    public studentModel updateStudent(studentModel student, String studentId) throws StudentCollectionException;

    /**
     * Delete student in the collection
     * @param id
     * @return 
     * @throws StudentCollectionException
     */
    public void deleteStudent(String id) throws StudentCollectionException;
}
