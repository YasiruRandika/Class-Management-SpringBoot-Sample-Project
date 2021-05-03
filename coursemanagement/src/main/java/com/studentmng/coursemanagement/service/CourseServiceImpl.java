package com.studentmng.coursemanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.studentmng.coursemanagement.exception.CourseCollectionException;
import com.studentmng.coursemanagement.model.CourseModel;
import com.studentmng.coursemanagement.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author W.G. Yasiru Randika
 * Course Service Interface
 * @version 1.0.0
 */

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Override
    public List<CourseModel> getAllCourses() {
        List<CourseModel> courses = courseRepository.findAll();
        return courses;
    }

    @Override
    public CourseModel getCourseById(String id) throws CourseCollectionException {
        Optional<CourseModel> course = courseRepository.findById(Long.parseLong(id));

        if (!course.isPresent()) {
            throw new CourseCollectionException(CourseCollectionException.notFoundException(Long.parseLong(id)));
        }
        return course.get();
    }

    @Override
    public CourseModel addCourse(CourseModel course) throws CourseCollectionException {
        Optional<List<CourseModel>> courseIn = courseRepository.findByName(course.getName());

        if (courseIn.get().size() >= 1) {
            throw new CourseCollectionException(CourseCollectionException.courseAlreadyExists(course.getName()));
        }

        course.setId(sequenceGeneratorService.generateSequence(CourseModel.SEQUENCE_NAME));
        course.setCreatedAt(new Date(System.currentTimeMillis()));
        course.setModifiedAt(new Date(System.currentTimeMillis()));

        courseRepository.save(course);

        return course;
    }

    @Override
    public CourseModel updateCourse(CourseModel course, String courseId) throws CourseCollectionException {
        Optional<List<CourseModel>> courseIn = courseRepository.findByName(course.getName());

        if (courseIn.get().size() > 1) {
            throw new CourseCollectionException(CourseCollectionException.courseAlreadyExists(course.getName()));
        }

        Optional<CourseModel> courseUpd = courseRepository.findById(Long.parseLong(courseId));

        if (!courseUpd.isPresent()) {
            throw new CourseCollectionException(CourseCollectionException.notFoundException(Long.parseLong(courseId)));
        }

        CourseModel myCourse = courseUpd.get();

        myCourse.setName(course.getName() != null ? course.getName() : myCourse.getName());
        myCourse.setDescription(course.getDescription() != null ? course.getDescription() : myCourse.getDescription());
        myCourse.setModifiedAt(new Date(System.currentTimeMillis()));

        return myCourse;
    }

    @Override
    public void deleteCourse(String id) throws CourseCollectionException {
        Optional<CourseModel> course = courseRepository.findById(Long.parseLong(id));

        if (!course.isPresent()) {
            throw new CourseCollectionException(CourseCollectionException.notFoundException(Long.parseLong(id)));
        }

        courseRepository.deleteById(Long.parseLong(id));
    }
    
}
