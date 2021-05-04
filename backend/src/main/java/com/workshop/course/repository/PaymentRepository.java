package com.workshop.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.course.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
