package com.hospitalms.model.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "success", "total", "data" })
@JsonInclude(Include.NON_NULL)
public class SingleResponse<R> extends BaseResponse {

	private R data;
	private Integer total;

	/**
	 * Returns the total of records.
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
	 * Returns record set.
	 * @return
	 */
	public R getData() {
		return data;
	}

	/**
	 * Sets record in the record set.
	 * @param data
	 */
	public void setData(R data) {
		this.data = data;
		if (data != null)
			this.total = 1;
		else
			this.total = 0;
	}
}