package com.studentmng.usermanagement.exception;

public class StudentCollectionException extends Exception {
    public StudentCollectionException(String message) {
        super(message);
    }

    public static String notFoundException(String id) {
        return "Student with id " + id + " was not found";
    }

    public static String studentAlreadyExists(String id) {
        return "Student Already Exists with Student ID " + id;
    }
}
