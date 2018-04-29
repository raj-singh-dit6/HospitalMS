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

import com.hospitalms.dto.OccupancyDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.OccupancyService;

@RestController
@RequestMapping("/occupancy")
public class OccupancyController {

	@Autowired
	private OccupancyService occupancyService;

	private static final Logger LOG = LoggerFactory.getLogger(OccupancyController.class);

	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<OccupancyDto> getOccupancy(@PathVariable("id") Integer id) {
		SingleResponse<OccupancyDto> resp = new SingleResponse<OccupancyDto>();
		
		try {
			resp.setData(occupancyService.getOccupancy(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getOccupancy() ", e);
		}

		return resp;
	}

	/**
	 * 
	 * @param occupancyDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addOccupancy(@RequestBody OccupancyDto occupancyDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			occupancyService.addOccupancy(occupancyDto);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in addOccupancy() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param occupancyDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateOccupancy(@RequestBody OccupancyDto occupancyDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			occupancyService.updateOccupancy(occupancyDto);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in updateOccupancy() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteOccupancy(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			occupancyService.deleteOccupancy(id);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in deleteOccupancy() ", e);
		}
		return resp;
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<OccupancyDto> getOccupancies() {
		Response<OccupancyDto> resp = new Response<OccupancyDto>();
		
		try {
			resp.setData(occupancyService.getOccupancies());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getOccupancies() ", e);
		}
		return resp;
	}

}