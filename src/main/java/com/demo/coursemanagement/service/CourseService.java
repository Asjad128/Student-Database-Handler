package com.demo.coursemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.coursemanagement.entity.Course;
import com.demo.coursemanagement.exception.ResourceNotFoundException;
import com.demo.coursemanagement.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Create Course
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // Get All Courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get Course By ID
    public Course getCourseById(Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID " + id + " not found"));
    }

    // Delete Course
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID " + id + " not found"));

        courseRepository.delete(course);
    }
}