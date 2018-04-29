package com.hospitalms.model.exceptions;


/**
 * Required Field Exception class is thrown when a record is missing a property that should exist in the data.
 * 
 * @author chadsherwood
 *
 */
public class RequiredFieldException extends BaseException {
	
	private static final long serialVersionUID = 1L;
	private String propertyName;

	/**
	 * Default Constructor
	 */
	public RequiredFieldException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public RequiredFieldException(Integer status, String propertyName) {
		this.error = "The property '" + propertyName + "' is a required field and must be entered.";
		this.setPropertyName(propertyName);
		this.status = status;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}
