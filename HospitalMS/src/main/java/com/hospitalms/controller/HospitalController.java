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

import com.hospitalms.dto.HospitalDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.HospitalService;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	private static final Logger LOG = LoggerFactory.getLogger(HospitalController.class);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<HospitalDto> getHospital(@PathVariable("id") Integer id) {
		SingleResponse<HospitalDto> resp = new SingleResponse<HospitalDto>();
		
		resp.setTotal(0);
		try {
			resp.setTotal(1);
			resp.setData(hospitalService.getHospital(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getHospital() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param hospitalDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addHospital(@RequestBody HospitalDto hospitalDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			hospitalService.addHospital(hospitalDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in addHospital() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param hospitalDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateHospital(@RequestBody HospitalDto hospitalDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			hospitalService.updateHospital(hospitalDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updateHospital() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{hospitalId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteHospital(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			hospitalService.deleteHospital(id);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deleteHospital() ", e);
		}
		return resp;
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<HospitalDto> getHospitals() {
		Response<HospitalDto> resp = new Response<HospitalDto>();
		
		try {
			resp.setData(hospitalService.getHospitals());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getHospitals() ", e);
		}
		return resp;
	}

}