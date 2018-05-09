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
import com.hospitalms.dto.PatientStatusDailyDataSet;
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
	 * Adds a Patient record with new Patient record values in @RequestBody patientDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addPatient(@RequestBody PatientDto patientDto) {
		CrudResponse resp = new CrudResponse();
		try {
			patientService.addPatient(patientDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in addPatient() ", e);
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
	
	@PostMapping(value = "/assign/doctor", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse assignDoctor(@RequestBody PatientDto patientDto) {
		CrudResponse resp = new CrudResponse();
		resp.setSuccess(false);
		try {
			patientService.assignDoctor(patientDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updatePatient() ", e);
		}
		return resp;
	}
	
	@PostMapping(value = "/assign/room", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse assignRoom(@RequestBody PatientDto patientDto) {
		CrudResponse resp = new CrudResponse();
		resp.setSuccess(false);
		try {
			patientService.assignRoom(patientDto);
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
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
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
	
	/**
	 * Returns a list of Doctor records of a particular hospital.
	 * @param   
	 * @return
	 */
	@GetMapping(value = "/all/hospital/{hospitalId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<PatientDto> getPatientsByHospital(@PathVariable("hospitalId") Integer hospitalId) {
		Response<PatientDto> resp = new Response<PatientDto>();
		
		try {
			resp.setData(patientService.getPatientsByHospital(hospitalId));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getDoctorsByHospital() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of Doctor records of a particular hospital.
	 * @param   
	 * @return
	 */
	@GetMapping(value = "/all/doctor/{doctorId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<PatientDto> getPatientsByDoctor(@PathVariable("doctorId") Integer doctorId) {
		Response<PatientDto> resp = new Response<PatientDto>();
		
		try {
			resp.setData(patientService.getPatientsByDoctor(doctorId));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getPatientsByDoctor() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of Doctor records of a particular hospital.
	 * @param   
	 * @return
	 */
	@GetMapping(value = "/all/room/{roomId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<PatientDto> getPatientsByRoom(@PathVariable("roomId") Integer roomId) {
		Response<PatientDto> resp = new Response<PatientDto>();
		
		try {
			resp.setData(patientService.getPatientsByRoom(roomId));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getPatientsByRoom() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a daily count of patient status in hospital @PathVariable hospitalId for current month.
	 * @param   
	 * @return
	 */
	@GetMapping(value = "/all/status/daily/{hospitalId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<PatientStatusDailyDataSet> getPatientsDailyStatus(@PathVariable("hospitalId") Integer hospitalId) {
		SingleResponse<PatientStatusDailyDataSet> resp = new SingleResponse<PatientStatusDailyDataSet>();
		
		try {
			resp.setData(patientService.getPatientsDailyStatus(hospitalId));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getPatientsDailyStatus() ", e);
		}
		return resp;
	}

}