package com.workshop.course.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.course.entities.Client;
import com.workshop.course.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Transactional(readOnly=true)
	Page<Order> findByClient(Client client, Pageable pageRequest);
	
}
