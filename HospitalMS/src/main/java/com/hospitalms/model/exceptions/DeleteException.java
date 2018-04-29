package com.hospitalms.model.exceptions;


/**
 * Delete Exception class that is thrown when a record fails to be retired.
 * 
 * @author chadsherwood
 *
 */
public class DeleteException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public DeleteException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public DeleteException(Integer status, String error) {
		this.error = error;
		this.status = status;
	}
}
