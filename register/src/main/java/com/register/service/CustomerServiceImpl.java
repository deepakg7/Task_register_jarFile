package com.register.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.exceptions.GeneralException;
import com.register.exceptions.ResourceAlreadyExistsException;
import com.register.exceptions.ResourceNotFoundException;
import com.register.model.Contact;
import com.register.model.Customer;
import com.register.repository.IContactRepository;
import com.register.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {
//	boolean flag=true;

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private IContactService contactService;
	
	@Autowired
	private IContactRepository contactRepository;


	@Override
	public Customer saveCustomer(Customer customer) {
		String email = customer.getEmailId();


	    Customer existingCustomer = customerRepository.findByEmailId(email);
	    if (existingCustomer != null) {
	        throw new ResourceAlreadyExistsException("Email id is already in use.");
	    }

	    // Ensure that at least one contact is associated with the customer
	    List<Contact> contacts = customer.getContact();
	    if (contacts == null || contacts.isEmpty()) {
	        throw new ResourceNotFoundException("At least one contact is required for the customer.");
	    }
	    for (Contact contact : contacts) {
	    	
	    	validateContactAge(contact.getDateOfBirth());
	    }
	    
	    // Save the customer and obtain the saved customer with generated ID
	    Customer savedCustomer = customerRepository.save(customer);

	    // Set the customerId of each contact before saving it
	    for (Contact contact : contacts) {
	    	
	        contact.setCustomerId(savedCustomer.getCustomerId());
	    }

	    // Save all contacts associated with the customer in a single call
	    contactRepository.saveAll(contacts);

	    return savedCustomer;

	}

	
	@Override
	public Customer updateData(Customer customer, int customerId) {
//		get the user details which to be updated by passing the user id
		Optional<Customer> cCustomer = this.customerRepository.findById(customerId);

		Customer c1 = null;
		Customer updatedData = null;
    	
//    	Checking whether user id exists or not
        if(cCustomer.isPresent())
        {
        	System.out.println("Record Exists and ready for Update !!!");
        	
//        	Extracting the user details as user object from optional
        	c1 = cCustomer.get();
 
//        	setting the updated value to setter method by taking from user through getter
        	c1.setFirstName(customer.getFirstName());
        	c1.setLastName(customer.getLastName());
        	c1.setAddress(customer.getAddress());
        	c1.setEmailId(customer.getEmailId());
        	c1.setMobileNo(customer.getMobileNo());
       
//        	saving the final updated value to db
        	updatedData = this.customerRepository.save(c1);
        	
        }

//        returning the updated value to user
        return updatedData;
	}
	
	
	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		Customer customer=customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Id not found"));
		customer.setContact((List<Contact>) contactService.getContactByCustomerId(customerId));
		
		return customer;
	}

	@Override
	public List<Customer> getAllData() {
		// TODO Auto-generated method stub
        List<Customer> list=customerRepository.findAll();
		for(Customer k:list)
		{

			k.setContact((List<Contact>) contactService.getContactByCustomerId(k.getCustomerId()));
//			System.out.println(k);

		}
		return list;
	}


	@Override
	public String delCustomer(int customerId) {
		Customer customer=customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Id not found"));
		customerRepository.delete(customer);
		contactService.delByCustomerId(customerId);
		return "Succesfully deleted";
	}


	
	private void validateContactAge(LocalDate dateOfBirth) {
		LocalDate currentDate = LocalDate.now();
		LocalDate birthDate = dateOfBirth;
		int age = Period.between(birthDate, currentDate).getYears();
		if (age <= 18) {
			throw new GeneralException("Contact age must be greater than 18.");
		}
	}
}
