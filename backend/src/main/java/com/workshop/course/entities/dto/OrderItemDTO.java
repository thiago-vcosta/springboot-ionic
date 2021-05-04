package com.workshop.course.entities.dto;

import com.workshop.course.entities.OrderItem;

public class OrderItemDTO {

	private Double discount;
	private Integer quantity;
	private Double price;

	public OrderItemDTO() {
	}

	public OrderItemDTO(Double discount, Integer quantity, Double price) {
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderItemDTO(OrderItem entity) {
		this.discount = entity.getDiscount();
		this.quantity = entity.getQuantity();
		this.price = entity.getPrice();
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
