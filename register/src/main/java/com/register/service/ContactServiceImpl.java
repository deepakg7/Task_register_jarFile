package com.register.service;


import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.exceptions.ResourceNotFoundException;
import com.register.model.Contact;
import com.register.model.Customer;
import com.register.repository.IContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

	@Autowired
	public IContactRepository contactRepository;
	
	@Override
	public Contact saveContact(Contact contact) {
		validateContactAge(contact.getDateOfBirth());
		return contactRepository.save(contact);
	}

	@Override
	public Contact updateData(Contact contact, int contactId) {
		Optional<Contact> conOptional = this.contactRepository.findById(contactId);

		Contact c1 = null;
		Contact updatedData = null;
    	
//    	Checking whether user id exists or not
        if(conOptional.isPresent())
        {
        	System.out.println("Record Exists and ready for Update !!!");
        	
//        	Extracting the user details as user object from optional
        	c1 = conOptional.get();
 
//        	setting the updated value to setter method by taking from user through getter
        	c1.setCFirstName(contact.getCFirstName());
        	c1.setCLastName(contact.getCLastName());
        	c1.setDateOfBirth(contact.getDateOfBirth());
        	validateContactAge(contact.getDateOfBirth());
       
//        	saving the final updated value to db
        	updatedData = this.contactRepository.save(c1);
        	
        }

//        returning the updated value to user
        return updatedData;
	}
	
	@Override
	public Contact getContactById(int contactId) {
		Optional<Contact> contactOptional =this.contactRepository.findById(contactId);
		Contact cObj = null;
		if(contactOptional.isPresent()){
			System.out.println("Record Exists and sending");
			cObj = contactOptional.get();
		}else {
			System.out.println("Agent Does not exists!");
		}
		return cObj;
	}

	@Override
	public List<Contact> getContactByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return contactRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Contact> getAllData() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}

	@Override
	public boolean delContact(int contactId) {
		Optional<Contact> conOptional =this.contactRepository.findById(contactId);
		if(conOptional.isPresent()) {
//			System.out.println("Contact DO NOT Exists for Deleting");
			this.contactRepository.deleteById(contactId);
			return true;
			
		}else {
			System.out.println("Error!");
			return false;
		}
    }

	@Override
	public boolean delByCustomerId(int customerId) {
		List<Contact> contact=contactRepository.findByCustomerId(customerId);
		contactRepository.deleteInBatch(contact);
		return true;
	}	

	private void validateContactAge(LocalDate dateOfBirth) {
		LocalDate currentDate = LocalDate.now();
		LocalDate birthDate = dateOfBirth;
		int age = Period.between(birthDate, currentDate).getYears();
		if (age <= 18) {
			throw new IllegalArgumentException("Contact age must be greater than 18.");
		}
	}

	@Override
	public String delAlContactsByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		
//		int flag=1;
//		
//		List<Contact> l=contactRepository.findByCustomerId(customerId);
//		int count=l.size();
//		for(Contact k:l)
//		{
//			if(flag==1)
//			{
//				System.out.print(false);
//				flag=0;
//			}
//			else {
//				
//				contactRepository.delete(k);
//			}
//		}
//		if(count>1)
//		{
//			return "Deleted all secondary contacts";
//		}
//		else {
//			return "You Cannot Delete Primary Contact";
//		}
//	}
		
		
		List<Contact> contacts = contactRepository.findByCustomerId(customerId);

	    if (contacts.size() <= 1) {
	        return "Cannot delete the only contact or no contacts present.";
	    } else {
	        Iterator<Contact> iterator = contacts.iterator();
	        iterator.next(); // Skip the first contact (assuming it's the primary contact)

	        while (iterator.hasNext()) {
	            Contact contactToDelete = iterator.next();
	            contactRepository.delete(contactToDelete);
	            break;
	        }

	        return "Deleted all secondary contacts.";
	    }
	}
}