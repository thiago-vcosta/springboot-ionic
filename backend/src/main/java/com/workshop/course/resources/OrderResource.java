package com.workshop.course.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.course.dto.OrderDTO;
import com.workshop.course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {

		OrderDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
}
