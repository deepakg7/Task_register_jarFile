package com.register.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

	public ResourceAlreadyExistsException() {
	    super("User Already Exists !!");
	}
	
	public ResourceAlreadyExistsException(String message) {
	    super(message);
	}
}
