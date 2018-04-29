package com.hospitalms.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoleDto implements Serializable {

	private Integer id;
	private String type	;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
}
