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

import com.hospitalms.dto.SpecialityDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.SpecialityService;

@RestController
@RequestMapping("/speciality")
public class SpecialityController {

	@Autowired
	private SpecialityService specialityService;

	private static final Logger LOG = LoggerFactory.getLogger(SpecialityController.class);

	/**
	 * Returns a single Speciality record corresponding to @param id.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<SpecialityDto> getSpeciality(@PathVariable("id") Integer id) {
		SingleResponse<SpecialityDto> resp = new SingleResponse<SpecialityDto>();
		try {
			resp.setData(specialityService.getSpeciality(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getSpeciality() ", e);
		}
		return resp;
	}

	/**
	 * Adds a speciality record with new speciality record values in @RequestBody
	 * specialityDto
	 * 
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addSpeciality(@RequestBody SpecialityDto specialityDto) {
		CrudResponse resp = new CrudResponse();

		try {
			specialityService.addSpeciality(specialityDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in addSpeciality() ", e);
		}
		return resp;
	}

	/**
	 * Updates the Speciality record with updated values in @RequestBody
	 * specialityDto
	 * 
	 * @param specialityDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateSpeciality(@RequestBody SpecialityDto specialityDto) {
		CrudResponse resp = new CrudResponse();

		try {
			specialityService.updateSpeciality(specialityDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updateSpeciality() ", e);
		}
		return resp;
	}

	/**
	 * Deletes a Speciality record corresponding to @param id.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteSpeciality(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			specialityService.deleteSpeciality(id);
			;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deleteSpeciality() ", e);
		}
		return resp;
	}

	/**
	 * Returns a list of Speciality records.
	 * 
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<SpecialityDto> getSpecialities() {
		Response<SpecialityDto> resp = new Response<SpecialityDto>();
		try {
			resp.setData(specialityService.getSpecialities());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getSpecialitys() ", e);
		}
		return resp;
	}

}