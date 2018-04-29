package com.hospitalms.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospitalms.model.responses.Response;


@Component
public class RESTLogoutSuccessHandler implements LogoutSuccessHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(RESTLogoutSuccessHandler.class);
	
	private final ObjectMapper mapper;
	
	@Autowired
	RESTLogoutSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }
	
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
		try {
			if (authentication.isAuthenticated())
				authentication.setAuthenticated(false);
			
			response.setStatus(HttpServletResponse.SC_OK);
	        Response<Object> resp = new Response<Object>();
	        resp.setSuccess(true);
	  
	        PrintWriter writer = response.getWriter();
	        mapper.writeValue(writer, resp);
	        writer.flush();
		}
		catch (Exception e) {
			LOG.error("Exception in onLogoutSuccess() in RESTLogoutSuccessHandler class");
		}
    }
}