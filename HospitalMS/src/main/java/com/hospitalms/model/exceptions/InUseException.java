package com.hospitalms.model.exceptions;


/**
 * Delete Exception class that is thrown when a record is in use.
 * 
 * @author chadsherwood
 *
 */
public class InUseException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public InUseException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public InUseException(Integer status, String error) {
		this.error = error;
		this.status = status;
	}
}
