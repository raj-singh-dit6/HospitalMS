package com.hospitalms.model.exceptions;

public class BaseException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	 public Integer status;
	 public String error;

	/**
	 * Default Constructor
	 */
	public BaseException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public BaseException(Integer status, String error) {
		this.error = error;
		this.status = status;
	}
	
	/**
	 * Returns the error message.
	 * 
	 * @return String
	 */
	public String getError() {
		return this.error;
	}
	
	/**
	 * Returns the error code.
	 * 
	 * @return Integer
	 */
	public Integer getStatus() {
		return this.status;
	}
	
	/**
	 * Updates the error message.
	 * 
	 * @return String
	 */
	public void setError(String error) {
		this.error =error;
	}
	
	/**
	 * Updates the error code.
	 * 
	 * @return Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
