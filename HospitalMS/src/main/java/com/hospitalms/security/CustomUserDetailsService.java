package com.hospitalms.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.model.Role;
import com.hospitalms.model.User;
import com.hospitalms.repository.UserRepository;

@Service("customUserDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	
	/**
	 * Loads user details into current security context.
	 */
	public UserDetails loadUserByUsername(String userName) {
		System.err.println("userName : " + userName);
		User user = userRepository.findByUserName(userName);
		LOG.info("User { } ", user);
		if (user == null) {
			LOG.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		CurrentUser currentUser = new CurrentUser(user.getUserName(), user.getPassword(), true, true, true, true,
				getGrantedAuthorities(user));
		currentUser.setUserName(userName);
		currentUser.setEmail(user.getEmail());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setRoles(user.getUserRoles());
		return currentUser;
	}

	/**
	 * Provides list of only granted autorities.
	 * @param user
	 * @return authorities
	 */
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getUserRoles()) {
			LOG.info("Role : {}", role);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType()));
		}
		LOG.info("authorities : {}", authorities);
		return authorities;
	}
}
