package com.demo.coursemanagement.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private Integer age;

    private String city;


    // =========================
    // ONE-TO-ONE
    // Student -> Address
    // =========================

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    // =========================
    // MANY-TO-ONE
    // Student -> Department
    // =========================

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    // =========================
    // MANY-TO-MANY
    // Student -> Courses
    // =========================

    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();


    // =========================
    // Constructors
    // =========================

    public Student() {
    }

    public Student(Long id, String name, String email,
                   Integer age, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.city = city;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;

        if (address != null && address.getStudent() != this) {
            address.setStudent(this);
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    // =========================
    // Course Helper Methods
    // =========================

    public void addCourse(Course course) {

        courses.add(course);

        if (!course.getStudents().contains(this)) {
            course.getStudents().add(this);
        }
    }

    public void removeCourse(Course course) {

        courses.remove(course);
        course.getStudents().remove(this);
    }
}