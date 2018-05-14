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

import com.hospitalms.dto.TestReportDto;
import com.hospitalms.model.responses.CrudResponse;
import com.hospitalms.model.responses.Response;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.TestReportService;

@RestController
@RequestMapping("/testReport")
public class TestReportController {
 
	@Autowired
	private TestReportService testReportService;
	
	private static final Logger LOG = LoggerFactory.getLogger(TestReportController.class);

	/**
	 * Returns a single TestReport record  corresponding to @param id.  
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public SingleResponse<TestReportDto> getTestReport(@PathVariable("id") Integer id) {
		SingleResponse<TestReportDto> resp = new SingleResponse<TestReportDto>();
		
		try {
			resp.setData(testReportService.getTestReport(id));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getTestReport() ", e);
		}

		return resp;
	}

	/**
	 * Adds a TestReport record with new TestReport record values in @RequestBody testReportDto
	 * @return
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse addTestReport(@RequestBody TestReportDto testReportDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			testReportService.addTestReport(testReportDto);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in addTestReport() ", e);
		}
		return resp;
	}

	/**
	 * Updates the TestReport record with updated values in @RequestBody testReportDto
	 * @param testReportDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse updateTestReport(@RequestBody TestReportDto testReportDto) {
		CrudResponse resp = new CrudResponse();
		
		try {
			testReportService.updateTestReport(testReportDto);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in updateTestReport() ", e);
		}
		return resp;
	}

	/**
	 * Deletes a TestReport record corresponding to @param id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CrudResponse deleteTestReport(@PathVariable("id") Integer id) {
		CrudResponse resp = new CrudResponse();
		try {
			testReportService.deleteTestReport(id);
			resp.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception in deleteTestReport() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of TestReport records.  
	 * @return
	 */
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<TestReportDto> getTestReports() {
		Response<TestReportDto> resp = new Response<TestReportDto>();
		
		try {
			resp.setData(testReportService.getTestReports());
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getTestReports() ", e);
		}
		return resp;
	}
	
	/**
	 * Returns a list of TestReport records.  
	 * @return
	 */
	@GetMapping(value = "/all/patient/{patientId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response<TestReportDto> getTestReportsByPatient(@PathVariable("patientId")Integer patientId) {
		Response<TestReportDto> resp = new Response<TestReportDto>();
		
		try {
			resp.setData(testReportService.getTestReportsByPatient(patientId));
			resp.setSuccess(true);
		} catch (Exception e) {

			LOG.error("Exception in getTestReportsByPatient() ", e);
		}
		return resp;
	}

}