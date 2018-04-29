package com.hospitalms.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospitalms.dto.UserDto;
import com.hospitalms.dto.UserInfoDto;
import com.hospitalms.model.User;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.repository.UserRepository;
import com.hospitalms.service.UserService;
import com.hospitalms.service.UserSessionService;

@Component
public class RESTAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private ObjectMapper mapper;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserSessionService userSessionService;

	@Autowired
    MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		this.mapper=customJackson2HttpMessageConverter.getObjectMapper();
		
		SingleResponse<UserInfoDto> resp = new SingleResponse<UserInfoDto>();
		CurrentUser cu = (CurrentUser) authentication.getPrincipal();
		try {
			User user = userRepo.findByUserName(cu.getUserName());
			UserInfoDto userSessionDTO= userSessionService.getUserSession(user.getUserName());
			resp.setData(userSessionDTO);
			resp.setSuccess(true);
		} catch (Exception e) {
			logger.error("Exception at onAuthenticationSuccess() ",e); 
		}
		PrintWriter writer = response.getWriter();
		mapper.writeValue(writer, resp)	;
		writer.flush();
	}
}