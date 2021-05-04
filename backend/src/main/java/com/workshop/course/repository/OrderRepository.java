package com.workshop.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.course.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
