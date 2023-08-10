package com.register.controller;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.register.model.Customer;
import com.register.service.ICustomerService;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

	
	@Autowired
	private ICustomerService customerService;


	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	
	@PostMapping("/save")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		logger.info("Creating customer: {}", customer);
		Customer savedCustomer = customerService.saveCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}

	@GetMapping("/GetAllCustomer")
	public ResponseEntity<?> getAllCustomer() {
		 logger.info("Getting all customers");
		List<Customer> customer = customerService.getAllData();
		ResponseEntity<?> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);

		return responseEntity;
	}

	@GetMapping("/GetCustomerById/{customerId}")
	public ResponseEntity<?> getCustomerByIdHandler(@PathVariable int customerId) {
		 logger.info("Getting customer by ID: {}", customerId);
		Customer customer = this.customerService.getCustomerById(customerId);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/updateData/{customerId}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable int customerId){
		 logger.info("Updating customer with ID: {}", customerId);
		Customer customer1=this.customerService.updateData(customer, customerId);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(customer1, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@DeleteMapping("/deleteById/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId){
		 logger.info("Deleting customer with ID: {}", customerId);
		String status = this.customerService.delCustomer(customerId);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(status,HttpStatus.OK);
		return responseEntity;
		
	}
}