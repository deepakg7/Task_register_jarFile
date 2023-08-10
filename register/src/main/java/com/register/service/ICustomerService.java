package com.register.service;

import java.util.List;

import com.register.exceptions.ResourceNotFoundException;
import com.register.model.Customer;

public interface ICustomerService {

	public Customer saveCustomer(Customer customer);
	
	public Customer updateData(Customer customer, int customerId);
	
	public Customer getCustomerById(int customerId) throws ResourceNotFoundException;
	
	public List<Customer> getAllData();
	
	public String delCustomer(int customerId);
}
