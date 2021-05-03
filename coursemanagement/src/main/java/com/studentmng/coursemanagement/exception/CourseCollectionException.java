package com.studentmng.coursemanagement.exception;

public class CourseCollectionException extends Exception {
    public CourseCollectionException(String message) {
        super(message);
    }

    public static String notFoundException(Long id) {
        return "Course with id " + id + " was not found";
    }

    public static String courseAlreadyExists(String name) {
        return "Course Already Exists with Same Name " + name;
    }
}
