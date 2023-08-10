package com.register.controller;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.register.model.Contact;

import com.register.service.IContactService;
@RestController
@RequestMapping("/contact")
@CrossOrigin(origins="*")
public class ContactController {

	@Autowired
	private IContactService contactService;
	
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@PostMapping("/saveContact")
	public ResponseEntity<Contact> createCustomer(@RequestBody Contact contact) {
		logger.info("Creating contact: {}", contact);
		Contact newContact = contactService.saveContact(contact);
		return ResponseEntity.status(HttpStatus.CREATED).body(newContact);
	}
	
	@DeleteMapping("/deleteContactByCId/{customerId}")
	public ResponseEntity<?> deleteContact(@PathVariable int customerId)
	{
		return new ResponseEntity<>(contactService.delAlContactsByCustomerId(customerId), HttpStatus.OK);
			
	}
	
	@GetMapping("/getAllContactByCId/{customerId}")
	public ResponseEntity<?> getAllContactsByCId(@PathVariable int customerId)
	{
		List<Contact> con = contactService.getContactByCustomerId(customerId);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(con,HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/updateContact/{contactId}")
	public ResponseEntity<?> updateCustomer(@RequestBody Contact contact, @PathVariable int contactId){
		Contact contact1=this.contactService.updateData(contact, contactId);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(contact1, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/getContactById/{contactId}")
	public ResponseEntity<?> getContactHandler(@PathVariable int contactId)
	{
		Contact contact = this.contactService.getContactById(contactId);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(contact,HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/delContactById/{contactId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int contactId){
		
		boolean status = this.contactService.delContact(contactId);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(status,HttpStatus.OK);
		return responseEntity;
		
	}
}
