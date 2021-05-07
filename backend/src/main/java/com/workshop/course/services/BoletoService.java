package com.workshop.course.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.workshop.course.entities.PaymentBoleto;

@Service
public class BoletoService {

	public void fillPaymentWithBoleto(PaymentBoleto pay, Date paymentInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(paymentInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setExpireDate(cal.getTime());
	}
	
}
