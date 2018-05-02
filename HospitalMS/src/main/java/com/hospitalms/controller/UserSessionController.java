package com.hospitalms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.service.UserSessionService;

@RestController
@RequestMapping("/userSession")
public class UserSessionController {

	@Autowired
	private UserSessionService userSessionService;

	private static final Logger LOG = LoggerFactory.getLogger(UserSessionController.class);

	/**
	 * Deletes a UserSession record corresponding to @param sessionKey.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{sessionKey}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteUserSession(@PathVariable("sessionKey") String sessionKey) {
		CrudResponse resp = new CrudResponse();
		try {
			userSessionService.deleteUserSession(sessionKey);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deleteUserSession() ", e);
		}
		return resp;
	}
}