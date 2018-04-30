
package com.hospitalms.dto;

import com.hospitalms.model.Prescription;

import lombok.Data;

@Data
public class DocumentDto{
    
    private Integer id; 
    private String name;
    private String description;
    private String type;
    private String location;
    private Prescription  prescription;

	
} 
