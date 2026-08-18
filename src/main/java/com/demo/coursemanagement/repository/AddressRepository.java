package com.demo.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.coursemanagement.entity.Address;

public interface AddressRepository
        extends JpaRepository<Address, Long> {

}