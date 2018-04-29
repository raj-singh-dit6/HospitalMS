package com.hospitalms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalms.dto.UserDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<UserDto> getUser(@PathVariable("id") Integer id) {
		SingleResponse<UserDto> resp = new SingleResponse<UserDto>();
		
		try {
			resp.setData(userService.getUser(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getUser() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param userDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addUser(@RequestBody UserDto userDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			userService.addUser(userDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in addUser() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param userDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateUser(@RequestBody UserDto userDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			userService.updateUser(userDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updateUser() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteUser(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			userService.deleteUser(id);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deleteUser() ", e);
		}
		return resp;
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<UserDto> getUsers() {
		Response<UserDto> resp = new Response<UserDto>();
		
		try {
			resp.setData(userService.getUsers());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getUsers() ", e);
		}
		return resp;
	}

}