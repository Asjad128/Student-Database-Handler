package com.demo.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.coursemanagement.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}