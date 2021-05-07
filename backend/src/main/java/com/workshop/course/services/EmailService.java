package com.workshop.course.services;

import org.springframework.mail.SimpleMailMessage;

import com.workshop.course.entities.Order;

public interface EmailService {

	void sendOrderCOnfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
}
