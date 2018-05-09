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

import com.hospitalms.dto.DoctorDto;
import com.hospitalms.dto.PatientDoctorDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.PatientDoctorService;

@RestController
@RequestMapping("/patientDoctor")
public class PatientDoctorController {

	@Autowired
	private PatientDoctorService patientDoctorService;
	
	private static final Logger LOG = LoggerFactory.getLogger(PatientDoctorController.class);

	/**
	 * Returns a single PatientDoctor record  corresponding to @param id.  
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<PatientDoctorDto> getPatientDoctor(@PathVariable("id") Integer id) {
		SingleResponse<PatientDoctorDto> resp = new SingleResponse<PatientDoctorDto>();

		return resp;
	}

	/**
	 * Adds a PatientDoctor record with new PatientDoctor record values in @RequestBody patientDoctorDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addPatientDoctor(@RequestBody PatientDoctorDto patientDoctorDto) {
		CrudResponse resp = new CrudResponse();
		try {
			patientDoctorService.addPatientDoctor(patientDoctorDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in addPatientDoctor() ", e);
		}
		return resp;
	}

	/**
	 * Updates the PatientDoctor record with updated values in @RequestBody patientDoctorDto
	 * @param patientDoctorDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updatePatientDoctor(@RequestBody PatientDoctorDto patientDoctorDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			patientDoctorService.updatePatientDoctor(patientDoctorDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updatePatientDoctor() ", e);
		}
		return resp;
	}

	/**
	 * Deletes a PatientDoctor record corresponding to @param id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deletePatientDoctor(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			patientDoctorService.deletePatientDoctor(id);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deletePatientDoctor() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of PatientDoctor records.  
	 * @return
	 */
	@GetMapping(value = "/all/patient/{patientId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<DoctorDto> getPatientDoctorsByPatient(@PathVariable("patientId") Integer patientId) {
		Response<DoctorDto> resp = new Response<DoctorDto>();
		
		try {
			resp.setData(patientDoctorService.getPatientDoctorsByPatient(patientId));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getPatientDoctors() ", e);
		}
		return resp;
	}
	
}