package com.hospitalms.model.exceptions;


/**
 * Required Field Exception class is thrown when a record is missing a property that should exist in the data.
 * 
 * @author chadsherwood
 *
 */
public class MissingParameterException extends BaseException {
	
	private static final long serialVersionUID = 1L;
	private String parameterName;

	/**
	 * Default Constructor
	 */
	public MissingParameterException() {}
	
	/**
	 * Constructor
	 * 
	 * @param status Integer
	 * @param error String
	 */
	public MissingParameterException(Integer status, String parameterName) {
		this.error = "The parameter '" + parameterName + "' is a required parameter and must be entered.";
		this.setParameterName(parameterName);
		this.status = status;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
}
