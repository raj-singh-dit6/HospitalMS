package com.hospitalms.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalms.model.Role;

import lombok.Data;

@Data
public class CurrentUser extends User {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	@JsonIgnore
	private User userObject;
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private Set<Role> roles;

	/**
	 * Constructor to initialize security context with current user.
	 * 
	 * @param userName
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public CurrentUser(String userName, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}