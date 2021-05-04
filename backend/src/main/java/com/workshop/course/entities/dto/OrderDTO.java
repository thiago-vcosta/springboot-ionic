package com.workshop.course.entities.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.workshop.course.entities.Address;
import com.workshop.course.entities.Client;
import com.workshop.course.entities.Order;
import com.workshop.course.entities.OrderItem;
import com.workshop.course.entities.Payment;

public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Instant instant;
	private Payment payment;

	private Client client;
	private Address shippingAddress;

	Set<OrderItemDTO> items = new HashSet<>();

	public OrderDTO() {
	}

	public OrderDTO(Long id, Instant instant, Payment payment, Client client, Address shippingAddress) {
		this.id = id;
		this.instant = instant;
		this.payment = payment;
		this.client = client;
		this.shippingAddress = shippingAddress;
	}

	public OrderDTO(Order entity) {
		this.id = entity.getId();
		this.instant = entity.getInstant();
		this.payment = entity.getPayment();
		this.client = entity.getClient();
		this.shippingAddress = entity.getShippingAddress();
	}

	public OrderDTO(Order entity, Set<OrderItem> items) {
		this(entity);
		entity.getItems().forEach(i -> this.items.add(new OrderItemDTO(i)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Set<OrderItemDTO> getItems() {
		return items;
	}

}
