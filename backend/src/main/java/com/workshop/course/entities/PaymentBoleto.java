package com.workshop.course.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workshop.course.entities.enums.PaymentStatus;

@Entity(name = "tb_payment_boleto")
@JsonTypeName("paymentWithBoleto")
public class PaymentBoleto extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Date paymentDue;
	private Date paymentDate;
	
	public PaymentBoleto() {		
	}

	public PaymentBoleto(Long id, PaymentStatus status, Order order, Date expireDate, Date expirePayment) {
		super(id, status, order);
		this.paymentDue = expireDate;
	}

	public Date getExpireDate() {
		return paymentDue;
	}

	public void setExpireDate(Date expireDate) {
		this.paymentDue = expireDate;
	}

	public Date getExpirePayment() {
		return paymentDate;
	}

	public void setExpirePayment(Date expirePayment) {
		this.paymentDate = expirePayment;
	}
	
}
