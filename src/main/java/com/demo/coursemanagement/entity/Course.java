package com.demo.coursemanagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String duration;


    // =========================
    // MANY-TO-MANY
    // Course -> Students
    // =========================

    @ManyToMany(mappedBy = "courses")
    @JsonBackReference("student-course")
    private List<Student> students = new ArrayList<>();


    // =========================
    // Constructors
    // =========================

    public Course() {
    }

    public Course(Long id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}