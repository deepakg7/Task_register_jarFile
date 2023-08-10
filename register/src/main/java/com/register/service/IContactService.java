package com.register.service;

import java.util.List;

import com.register.model.Contact;

public interface IContactService {

	public Contact saveContact(Contact contact);
	
	public Contact updateData(Contact contact, int contactId);
	
	public Contact getContactById(int contactId);
	
	List<Contact> getContactByCustomerId(int customerId);

	
	public List<Contact> getAllData();
	
	public boolean delContact(int contactId);
	
	public boolean delByCustomerId(int customerId);
	
	public String delAlContactsByCustomerId(int customerId);
}
