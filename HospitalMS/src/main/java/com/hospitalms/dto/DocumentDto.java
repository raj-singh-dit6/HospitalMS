
package com.hospitalms.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.hospitalms.model.Prescription;

import lombok.Data;

@Data
public class DocumentDto implements Serializable{
    
    private Integer id; 
    private String name;
    private String description;
    private String type;
    private String location;
    private Prescription  prescription;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;

	
} 
