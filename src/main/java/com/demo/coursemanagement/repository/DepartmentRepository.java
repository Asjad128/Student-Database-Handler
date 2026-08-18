package com.demo.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.coursemanagement.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}