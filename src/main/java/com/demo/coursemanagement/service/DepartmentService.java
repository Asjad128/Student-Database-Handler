package com.demo.coursemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.coursemanagement.entity.Department;
import com.demo.coursemanagement.exception.ResourceNotFoundException;
import com.demo.coursemanagement.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Create Department
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get All Departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get Department By ID
    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department with ID " + id + " not found"));
    }

    // Delete Department
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department with ID " + id + " not found"));

        departmentRepository.delete(department);
    }
}