package net.viralpatel.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hai.payment.PaymentHandler;
import hai.payment.dynamodb_access.PaymentRepository;
import hai.payment.model.PaymentPto;
import net.viralpatel.spring.model.Customer;

@RestController
public class PaymentController {
	
	@PostMapping(value = "/payments/table")
	public ResponseEntity createPaymentTable(@RequestBody Customer customer) {

		PaymentRepository pr = new PaymentRepository();
		pr.createTable();
		return new ResponseEntity(true, HttpStatus.OK);
	}
	
	@PostMapping(value = "/payments/create")
	public ResponseEntity insertPayment(@RequestBody PaymentPto payment) {

		PaymentRepository pr = new PaymentRepository();
		pr.insertItem(payment);
		return new ResponseEntity(true, HttpStatus.OK);
	}
	
	@GetMapping("/payments")
	public List<PaymentPto> getPayments() {

		PaymentRepository pr = new PaymentRepository();
		return pr.getAll();
	}
}
