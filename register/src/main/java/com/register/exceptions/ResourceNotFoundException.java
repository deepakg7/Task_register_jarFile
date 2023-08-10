package com.register.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
	    super("User not found !!");
	}

	public ResourceNotFoundException(String message) {
	    super(message);
	}
	
}

