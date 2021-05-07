package com.workshop.course.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.workshop.course.entities.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Order obj);

	void sendHtmlEmail(MimeMessage msg);
}
