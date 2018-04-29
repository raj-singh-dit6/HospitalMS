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

import com.hospitalms.dto.BillStatusDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.BillStatusService;

@RestController
@RequestMapping("/billStatus")
public class BillStatusController {

	@Autowired
	private BillStatusService billStatusService;

	private static final Logger LOG = LoggerFactory.getLogger(BillStatusController.class);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<BillStatusDto> getBillStatus(@PathVariable("id") Integer id) {
		SingleResponse<BillStatusDto> resp = new SingleResponse<BillStatusDto>();
		resp.setSuccess(false);
		try {
			resp.setData(billStatusService.getBillStatus(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getBillStatus() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param billStatusDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addBillStatus(@RequestBody BillStatusDto billStatusDto) {
		CrudResponse resp = new CrudResponse();
		resp.setSuccess(false);
		try {
			billStatusService.addBillStatus(billStatusDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in addBillStatus() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param billStatusDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateBillStatus(@RequestBody BillStatusDto billStatusDto) {
		CrudResponse resp = new CrudResponse();
		resp.setSuccess(false);
		try {
			billStatusService.updateBillStatus(billStatusDto);
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in updateBillStatus() ", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{billStatusId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteBillStatus(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		resp.setSuccess(false);
		try {
			billStatusService.deleteBillStatus(id);;
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in deleteBillStatus() ", e);
		}
		return resp;
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<BillStatusDto> getBillStatuses() {
		Response<BillStatusDto> resp = new Response<BillStatusDto>();
		resp.setSuccess(false);
		try {
			resp.setData(billStatusService.getBillStatuses());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getBillStatuss() ", e);
		}
		return resp;
	}

}