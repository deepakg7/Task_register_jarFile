package com.register.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int contactId;
	private int customerId;
	
	@NotBlank(message = "Contacts First Name should not be blank")
	private String cFirstName;
	
	@NotBlank(message = "Contacts Last Name should not be blank")
	private String cLastName;
	
	@NotBlank(message = "Date of birth cannot be blank for Contact")
	private LocalDate dateOfBirth;


//	@ManyToOne
//	@JoinColumn(name = "customer_id")
//	private Customer customer;
}