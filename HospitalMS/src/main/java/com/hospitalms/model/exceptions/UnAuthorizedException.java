package com.hospitalms.model.exceptions;


/**
 * UnAuthorized Exception class is thrown when an user has not authorized to access the api calls.
 * 
 * @author Kiran
 *
 */
public class UnAuthorizedException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public UnAuthorizedException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public UnAuthorizedException(Integer status, String error) {
		this.error = error;
		this.status = status;
	}
}
