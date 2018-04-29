package com.hospitalms.model.exceptions;


/**
 * Missing Record Exception class that is thrown when a record is missing that should be there.
 * 
 * @author chadsherwood
 *
 */
public class MissingRecordException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public MissingRecordException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public MissingRecordException(Integer status, String error) {
		this.error = error;
		this.status = status;
	}
}
