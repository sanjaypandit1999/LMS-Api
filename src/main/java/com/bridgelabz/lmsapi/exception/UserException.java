package com.bridgelabz.lmsapi.exception;


/**
 * purpose to handle custom exception
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 *
 */
@SuppressWarnings("serial")
public class UserException extends RuntimeException {
	
	public UserException(String message) {
		super(message);
	}

}
