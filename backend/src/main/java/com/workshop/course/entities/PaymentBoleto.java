package com.workshop.course.entities;

import java.time.Instant;

import javax.persistence.Entity;

import com.workshop.course.entities.enums.PaymentStatus;

@Entity(name = "tb_payment_boleto")
public class PaymentBoleto extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Instant paymentDue;
	private Instant paymentDate;
	
	public PaymentBoleto() {		
	}

	public PaymentBoleto(Long id, PaymentStatus status, Order order, Instant expireDate, Instant expirePayment) {
		super(id, status, order);
		this.paymentDue = expireDate;
	}

	public Instant getExpireDate() {
		return paymentDue;
	}

	public void setExpireDate(Instant expireDate) {
		this.paymentDue = expireDate;
	}

	public Instant getExpirePayment() {
		return paymentDate;
	}

	public void setExpirePayment(Instant expirePayment) {
		this.paymentDate = expirePayment;
	}
	
}
