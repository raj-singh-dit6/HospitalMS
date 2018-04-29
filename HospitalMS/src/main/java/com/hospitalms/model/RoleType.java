package com.hospitalms.model;

import java.io.Serializable;

public enum RoleType implements Serializable{
	PATIENT("PATIENT"),
	HEAD("HEAD"),
	ADMIN("ADMIN"),
	DOCTOR("DOCTOR");
	
	String roleType;
	private RoleType(String roleType)
	{
		this.roleType=roleType;
		
	}

	public String getRoleType() {
		return roleType;
	}
}