package com.workshop.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.course.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
