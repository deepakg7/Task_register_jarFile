package com.register.exceptions;

public class GeneralException extends RuntimeException{

	public GeneralException() {
	    super(" General Exception !!");
	}
	
	public GeneralException(String message) {
	    super(message);
	}
}
