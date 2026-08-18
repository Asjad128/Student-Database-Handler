package com.demo.coursemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "door_no")
    private String doorNo;

    @Column
    private String street;

    @Column
    private String area;

    @Column
    private String state;

    @Column
    private String pincode;

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

    public Long getId() {
        return id;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public void setId(Long id) {
        this.id = id;
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

 
@OneToOne(mappedBy = "address")
    @JsonBackReference
    private Student student;
}