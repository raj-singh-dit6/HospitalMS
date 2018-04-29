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

import com.hospitalms.dto.PatientDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);

	/**
	 * Returns a single Patient record  corresponding to @param id.  
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<PatientDto> getPatient(@PathVariable("id") Integer id) {
		SingleResponse<PatientDto> resp = new SingleResponse<PatientDto>();
		resp.setSuccess(false);
		try {
			resp.setData(patientService.getPatient(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getPatient() ", e);
		}
		return resp;
	}

	/**
	 * Updates the Patient record with updated values in @RequestBody patientDto
	 * @param patientDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updatePatient(@RequestBody PatientDto patientDto) {
		CrudResponse resp = new CrudResponse();
		resp.setSuccess(false);
		try {
			patientService.updatePatient(patientDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updatePatient() ", e);
		}
		return resp;
	}

	/**
	 * Deletes a Patient record corresponding to @param id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{patientId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deletePatient(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			patientService.deletePatient(id);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deletePatient() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of Patient records.  
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<PatientDto> getPatients() {
		Response<PatientDto> resp = new Response<PatientDto>();
		resp.setSuccess(false);
		try {
			resp.setData(patientService.getPatients());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getPatients() ", e);
		}
		return resp;
	}

}