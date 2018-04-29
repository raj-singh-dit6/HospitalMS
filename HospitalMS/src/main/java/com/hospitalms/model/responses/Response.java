package com.hospitalms.model.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"success","total","data"})
@JsonInclude(Include.NON_NULL)
public class Response<R> extends BaseResponse {

	private List<R> data;
	private Integer total;

	/**
	 *  Constructor to initialize List<R>
	 */
	public Response() {
		data = new ArrayList<>();
	}
		
	/**
	 * Returns the total number of records when paginating.
	 * @return Integer
	 */
	public Integer getTotal() {
		return total;		
	}
	
	/**
	 * Sets the total records in the record set.
	 * @param total int
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
	/**
	 * Returns a list of records.
	 * @return
	 */
	public List<R> getData() {
		return data;
	}
	
	/**
	 * Sets a list of records & total number of records in the record set. 
	 * @param data
	 */
	public void setData(List<R> data) {
		this.data = data==null?new ArrayList<R>():data;
		if (data!=null)
			this.total = data.size();
		else
			this.total = 0;
	}
	
}