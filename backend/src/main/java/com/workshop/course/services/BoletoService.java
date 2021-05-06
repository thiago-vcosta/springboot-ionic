package com.workshop.course.services;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.workshop.course.entities.PaymentBoleto;

@Service
public class BoletoService {

	public void fillPaymentWithBoleto(PaymentBoleto pay, Instant paymentInstant) {
		Date cal = Date.from(paymentInstant);
		Calendar cal1 = Calendar.getInstance();
		
		cal1.setTime(cal);
		cal1.add(Calendar.DAY_OF_MONTH, 7);
		pay.setExpireDate(cal1.getTime().toInstant());
	}
	
}
