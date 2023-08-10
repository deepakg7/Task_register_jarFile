package com.register.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.register.model.Contact;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {


	public List<Contact> findByCustomerId(int customerId);
}
