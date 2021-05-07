package com.workshop.course.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.course.entities.Order;
import com.workshop.course.entities.OrderItem;
import com.workshop.course.entities.PaymentBoleto;
import com.workshop.course.entities.enums.PaymentStatus;
import com.workshop.course.repository.OrderItemRepository;
import com.workshop.course.repository.OrderRepository;
import com.workshop.course.repository.PaymentRepository;
import com.workshop.course.services.exeptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private EmailService emailService;

	@Transactional(readOnly = true)
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		Order entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found Id: " + id));
		return entity;
	}

	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.findById(obj.getClient().getId()));
		obj.getPayment().setStatus(PaymentStatus.PENDENTE);
		obj.getPayment().setOrder(obj);

		if (obj.getPayment() instanceof PaymentBoleto) {
			PaymentBoleto pay = (PaymentBoleto) obj.getPayment();
			boletoService.fillPaymentWithBoleto(pay, obj.getInstant());
		}

		obj = repository.save(obj);
		paymentRepository.save(obj.getPayment());

		for (OrderItem oi : obj.getItems()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.findById(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
}