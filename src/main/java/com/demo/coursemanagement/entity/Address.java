package com.demo.coursemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "door_no")
    private String doorNo;

    private String street;

    private String area;

    private String state;

    private String pincode;


    // =========================
    // ONE-TO-ONE
    // Address -> Student
    // =========================

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Student student;


    // =========================
    // Constructors
    // =========================

    public Address() {
    }

    public Address(Long id, String doorNo, String street,
                   String area, String state, String pincode) {
        this.id = id;
        this.doorNo = doorNo;
        this.street = street;
        this.area = area;
        this.state = state;
        this.pincode = pincode;
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

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}