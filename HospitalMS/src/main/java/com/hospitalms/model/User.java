package com.hospitalms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Data
@JsonInclude(Include.NON_NULL)
public class User implements Serializable{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
 
	@JsonIgnore
    @Column(length=30,unique=true, nullable=false)
    private String userName;
     
    @JsonIgnore
	@Column(length=100,nullable=false)
    private String password;
    
    @Column(length=30,nullable=false)
    private String firstName;

    @Column(length=30,nullable=true)
    private String lastName;
    
    @Column(nullable=true ,columnDefinition="String default ''")
    private String address;
    
   	@Column(nullable=true, columnDefinition = "String default ''")
    private Date dob;
       
	@Column(length=30,unique=true,nullable=false)
    private String email;
	
	@Column( nullable=false)
    private Long contact;
    
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "userRole", 
             joinColumns = { @JoinColumn(name = "userId") }, 
             inverseJoinColumns = { @JoinColumn(name = "roleId") })
	private Set<Role> userRoles = new HashSet<Role>();
	

	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="hosp_id")
	private Hospital hospital;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user", fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private Doctor doctor;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user", fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private Patient patient;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user", fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private Head head;
	
	@JsonIgnore
	@OneToOne(mappedBy="user",fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private UserSession userSession;
	 
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
