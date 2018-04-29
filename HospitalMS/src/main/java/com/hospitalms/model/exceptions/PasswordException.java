package com.hospitalms.model.exceptions;


/**
 * Password Record Exception class that is thrown when a record has an error when resetting password.
 * 
 * @author chadsherwood
 *
 */
public class PasswordException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public PasswordException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public PasswordException(Integer status, String error) {
		this.error = error;
		this.status = status;
	}
}
