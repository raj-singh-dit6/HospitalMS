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
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	private static final Logger LOG = LoggerFactory.getLogger(DoctorController.class);

	/**
	 * Returns a single Doctor record  corresponding to @param id.  
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<DoctorDto> getDoctor(@PathVariable("id") Integer id) {
		SingleResponse<DoctorDto> resp = new SingleResponse<DoctorDto>();
		
		try {
			resp.setData(doctorService.getDoctor(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getDoctor() ", e);
		}
		return resp;
	}


	/**
	 * Updates the Doctor record with updated values in @RequestBody doctorDto
	 * @param doctorDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateDoctor(@RequestBody DoctorDto doctorDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			doctorService.updateDoctor(doctorDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updateDoctor() ", e);
		}
		return resp;
	}

	/**
	 * Deletes a Doctor record corresponding to @param id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{doctorId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteDoctor(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			doctorService.deleteDoctor(id);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deleteDoctor() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of Doctor records.  
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<DoctorDto> getDoctors() {
		Response<DoctorDto> resp = new Response<DoctorDto>();
		
		try {
			resp.setData(doctorService.getDoctors());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getDoctors() ", e);
		}
		return resp;
	}

}