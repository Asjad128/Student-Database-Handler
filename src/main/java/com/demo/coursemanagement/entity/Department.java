package com.demo.coursemanagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    // =========================
    // ONE-TO-MANY
    // Department -> Students
    // =========================

    @OneToMany(
        mappedBy = "department",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonBackReference("student-department")
    private List<Student> students = new ArrayList<>();


    // =========================
    // Constructors
    // =========================

    public Department() {
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    // =========================
    // Getters and Setters
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    // =========================
    // Helper Methods
    // =========================

    public void addStudent(Student student) {

        students.add(student);

        if (student.getDepartment() != this) {
            student.setDepartment(this);
        }
    }

    public void removeStudent(Student student) {

        students.remove(student);

        if (student.getDepartment() == this) {
            student.setDepartment(null);
        }
    }
}