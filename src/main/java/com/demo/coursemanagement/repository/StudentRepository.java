package com.demo.coursemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.coursemanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Derived Query Methods

    List<Student> findByName(String name);

    List<Student> findByCity(String city);

    Student findByEmail(String email);

}