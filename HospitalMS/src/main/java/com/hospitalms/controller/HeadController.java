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

import com.hospitalms.dto.HeadDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.HeadService;

@RestController
@RequestMapping("/head")
public class HeadController {

	@Autowired
	private HeadService headService;
	
	private static final Logger LOG = LoggerFactory.getLogger(HeadController.class);

	/**
	 * Returns a single Head record  corresponding to @param id.  
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<HeadDto> getHead(@PathVariable("id") Integer id) {
		SingleResponse<HeadDto> resp = new SingleResponse<HeadDto>();
		
		try {
			resp.setData(headService.getHead(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getHead() ", e);
		}
		return resp;
	}

	/**
	 * Adds a Head record with new Head record values in @RequestBody headDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addHead(@RequestBody HeadDto headDto) {
		CrudResponse resp = new CrudResponse();
		try {
			headService.addHead(headDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in addHead() ", e);
		}
		return resp;
	}

	/**
	 * Updates the Head record with updated values in @RequestBody headDto
	 * @param headDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateHead(@RequestBody HeadDto headDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			headService.updateHead(headDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updateHead() ", e);
		}
		return resp;
	}

	/**
	 * Deletes a Head record corresponding to @param id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteHead(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			headService.deleteHead(id);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deleteHead() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of Head records.  
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<HeadDto> getHeads() {
		Response<HeadDto> resp = new Response<HeadDto>();
		
		try {
			resp.setData(headService.getHeads());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getHeads() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of Head records of a particular hospital.
	 * @param   
	 * @return
	 */
	@GetMapping(value = "/hospital/{hospitalId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<HeadDto> getHeadByHospital(@PathVariable("hospitalId") Integer hospitalId) {
		SingleResponse<HeadDto> resp = new SingleResponse<HeadDto>();
		
		try {
			resp.setData(headService.getHeadByHospital(hospitalId));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getHeadByHospital() ", e);
		}
		return resp;
	}

}