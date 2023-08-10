package com.register.model;


import jakarta.persistence.*;
import lombok.*;

import javax.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int customerId;
		
		@NotBlank(message = "First Name is required")
	    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name should not have numeric or special characters")
	    @Size(max = 30)
		private String firstName;
			
		@NotBlank(message = "Last Name is required")
	    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name should not have numeric or special characters")
	    @Size(max = 30)
		private String lastName;
		
		@NotBlank(message = "Email id is required")
	    @Email(message = "Invalid email format")
		private String emailId;
		
		@NotNull(message = "Mobile Number is required")
		@Min(value = 1000000000, message = "Mobile number must be a 10-digit number") @Max(value = 9999999999L, message = "Mobile number must be a 10-digit number")
		private Long mobileNo;
		 
		private String address;
		
		
		@Transient
	    private List<Contact> contact = new ArrayList<>();
	}