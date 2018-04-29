package com.hospitalms.security;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hospitalms.model.exceptions.UnAuthorizedException;
import com.hospitalms.service.UserService;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

	private UserService userService;

	String origin;

	/**
	 * Filter to allow http headers and request options.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (userService == null) {
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			userService = webApplicationContext.getBean(UserService.class);
		}

		try {

			// Allows CORS to work by setting the origin from the appropriate profile
			// settings file.
			res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
			res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
			res.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Authorization");
			res.setHeader("Access-Control-Allow-Credentials", "true");

			if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
				res.setStatus(HttpServletResponse.SC_OK);
			} else {
				if (req.getRequestURL().indexOf("/login") == -1 && req.getRequestURL().indexOf("/checkStatus") == -1
						&& req.getRequestURL().indexOf("/requestpasswordchange") == -1
						&& req.getRequestURL().indexOf("/changepassword") == -1
						&& req.getRequestURL().indexOf("/validate-token") == -1) {
					String authorizationHeader = req.getHeader("Authorization");

					if (authorizationHeader == null || authorizationHeader.equals("")
							|| !isAuthenticated(authorizationHeader)) {
						throw new UnAuthorizedException();
					} else {
						res.setStatus(HttpServletResponse.SC_OK);

					}
				}
				filterChain.doFilter(req, res);
			}
		} catch (UnAuthorizedException authenticationException) {
			SecurityContextHolder.clearContext();
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
		}
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

	/**
	 * Checks if user session exists.
	 * 
	 * @param authorizationHeader
	 * @return
	 */
	private boolean isAuthenticated(String authorizationHeader) {

		if (null == authorizationHeader)
			return false;
		authorizationHeader = authorizationHeader.replaceAll("userName=", "");
		final StringTokenizer tokenizer = new StringTokenizer(authorizationHeader, ";");
		final String username = tokenizer.nextToken();
		final String token = tokenizer.nextToken();
		return userService.authenticate(username, token);
	}

}