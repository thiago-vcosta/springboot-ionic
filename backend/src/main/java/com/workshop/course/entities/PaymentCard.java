package com.workshop.course.entities;

import javax.persistence.Entity;

import com.workshop.course.entities.enums.PaymentStatus;

@Entity(name = "tb_payment_card")
public class PaymentCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer installment;

	public PaymentCard() {
	}

	public PaymentCard(Long id, PaymentStatus status, Order order, Integer installment) {
		super(id, status, order);
		this.installment = installment;
	}

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

}
