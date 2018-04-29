package com.hospitalms.model.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "success", "error", "status","message"})
public class CrudResponse {

	private Boolean success=false;
	private Integer status;
	private String message;

}