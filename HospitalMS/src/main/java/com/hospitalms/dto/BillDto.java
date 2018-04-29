package com.hospitalms.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hospitalms.model.TestReport;

import lombok.Data;

@Data
public class BillDto {
	
	private Integer id;
    private Set<TestReport> testReports ;
	private Float amount;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
}
