package com.hospitalms.model.exceptions;


/**
 * DuplicateException class
 * 
 * @author chadsherwood
 *
 */
public class DuplicateException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public DuplicateException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public DuplicateException(Integer status, String error) {
		this.error = error;
		this.status = status;
	}
}
