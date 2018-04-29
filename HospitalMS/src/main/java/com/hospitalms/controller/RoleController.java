package com.hospitalms.controller;

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

import com.hospitalms.dto.RoleDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
 
	@Autowired
	private RoleService roleService;
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

	/**
	 * Returns a single Role record  corresponding to @param id.  
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<RoleDto> getRole(@PathVariable("id") Integer id) {
		SingleResponse<RoleDto> resp = new SingleResponse<RoleDto>();
		
		try {
			resp.setData(roleService.getRole(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getRole() ", e);
		}

		return resp;
	}

	/**
	 * Adds a Role record with new Role record values in @RequestBody roleDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addRole(@RequestBody RoleDto roleDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			roleService.addRole(roleDto);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in addRole() ", e);
		}
		return resp;
	}

	/**
	 * Updates the Role record with updated values in @RequestBody roleDto
	 * @param roleDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateRole(@RequestBody RoleDto roleDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			roleService.updateRole(roleDto);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in updateRole() ", e);
		}
		return resp;
	}

	/**
	 * Deletes a Role record corresponding to @param id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteRole(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			roleService.deleteRole(id);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in deleteRole() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of Role records.  
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<RoleDto> getOccupancies() {
		Response<RoleDto> resp = new Response<RoleDto>();
		
		resp.setTotal(0);
		try {
			resp.setData(roleService.getRoles());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getOccupancies() ", e);
		}
		return resp;
	}

}