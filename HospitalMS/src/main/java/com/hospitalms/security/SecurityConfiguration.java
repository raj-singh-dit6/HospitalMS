package com.hospitalms.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);
	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private RESTLogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	PersistentTokenRepository tokenRepository;

	/**
	 * Configures spring security parameter like entry point, failure handling , authentication provider, request url matchers, success handler.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll().and()
				.authenticationProvider(authenticationProvider()).formLogin();

		http.headers().frameOptions().sameOrigin();

		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin().successHandler(authenticationSuccessHandler);
		http.formLogin().failureHandler(authenticationFailureHandler);
		http.logout().logoutSuccessHandler(logoutSuccessHandler).clearAuthentication(true).permitAll();
	}

	/**
	 * Returns BCryptPasswordEncoder
	 * 
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configure  Authentication Provider for custom user details service.
	 * 
	 * @return authenticationProvider
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	/**
	 * Configure persistence of token for "remember-me" feature of spring security.
	 * @return tokenBasedservice
	 */
	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	/**
	 * Configures trust decision making unit for Authentication process.
	 * @return
	 */
	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

	/**
	 * Configures Cross-Origin for spring security if HIGHEST PRECEDENCE filter does not come into action.
	 * @return corsConfigurationSource
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
