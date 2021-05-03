package com.studentmng.coursemanagement.service;

import java.util.List;

import com.studentmng.coursemanagement.exception.CourseCollectionException;
import com.studentmng.coursemanagement.model.CourseModel;

/**
 * @author W.G. Yasiru Randika
 * Course Service Interface
 * @version 1.0.0
 */
public interface CourseService {
    /**
     * Get all courses in the collection
     * @param
     * @return List<CourseModel>
     */
    public List<CourseModel> getAllCourses();

    /**
     * Get a course in the collection by Id
     * @param id
     * @return CourseModel
     * @throws CourseCollectionException
     */
    public CourseModel getCourseById(String id) throws CourseCollectionException;

    /**
     * Add course to the collection
     * @param CourseModel
     * @return CourseModel
     * @throws CourseCollectionException
     */
    public CourseModel addCourse(CourseModel course) throws CourseCollectionException;

    /**
     * Update course in the collection
     * @param CourseModel
     * @return CourseModel
     * @throws CourseCollectionException
     */
    public CourseModel updateCourse(CourseModel course, String courseId) throws CourseCollectionException;

    /**
     * Delete course in the collection
     * @param id
     * @return 
     * @throws CourseCollectionException
     */
    public void deleteCourse(String id) throws CourseCollectionException;
}
