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

import com.hospitalms.dto.PatientStatusDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.PatientStatusService;

@RestController
@RequestMapping("/patientStatus")
public class PatientStatusController {

	@Autowired
	private PatientStatusService patientStatusService;

	private static final Logger LOG = LoggerFactory.getLogger(PatientStatusController.class);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<PatientStatusDto> getPatientStatus(@PathVariable("id") Integer id) {
		SingleResponse<PatientStatusDto> resp = new SingleResponse<PatientStatusDto>();
		
		try {
			resp.setData(patientStatusService.getPatientStatus(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getPatientStatus() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param patientStatusDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addPatientStatus(@RequestBody PatientStatusDto patientStatusDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			patientStatusService.addPatientStatus(patientStatusDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in addPatientStatus() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param patientStatusDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updatePatientStatus(@RequestBody PatientStatusDto patientStatusDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			patientStatusService.updatePatientStatus(patientStatusDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updatePatientStatus() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{patientStatusId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deletePatientStatus(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			patientStatusService.deletePatientStatus(id);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deletePatientStatus() ", e);
		}
		return resp;
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<PatientStatusDto> getPatientStatuses() {
		Response<PatientStatusDto> resp = new Response<PatientStatusDto>();
		resp.setTotal(0);
		
		try {
			resp.setData(patientStatusService.getPatientStatuses());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getPatientStatuss() ", e);
		}
		return resp;
	}

}