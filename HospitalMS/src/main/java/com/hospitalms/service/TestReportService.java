package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.TestReportDto;
import com.hospitalms.model.Bill;
import com.hospitalms.model.Patient;
import com.hospitalms.model.Test;
import com.hospitalms.model.TestReport;
import com.hospitalms.repository.BillRepository;
import com.hospitalms.repository.PatientRepository;
import com.hospitalms.repository.TestReportRepository;
import com.hospitalms.repository.TestRepository;


@Service("testReportService")
@Transactional
public class TestReportService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(TestReportService.class);

	@Autowired
	TestReportRepository testReportRepository;
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	BillRepository billRepository;
	
	
	@Autowired
	ModelMapper mapper;
	
	public List<TestReportDto> getTestReportsByPatient(Integer patientId) {
		Patient patient = patientRepository.findById(patientId).get();
		List<TestReport> testReportList=(List<TestReport>)testReportRepository.findAllByPatient(patient);
		List<TestReportDto> testReportDTOList = new ArrayList<TestReportDto>();
		for(TestReport testReport:testReportList)
		{
			TestReportDto testReportDto = new TestReportDto();
			testReportDto.setDescription(testReport.getDescription());
			testReportDto.setId(testReport.getId());
			testReportDto.setPatient(testReport.getPatient());
			testReportDto.setTest(testReport.getTest());
			testReportDto.setBill(testReport.getBill());
			
			testReportDTOList.add(testReportDto);
		}
		return testReportDTOList;
	}
	
	public List<TestReportDto> getTestReports() {
		List<TestReport> testReportList=(List<TestReport>)testReportRepository.findAll();
		List<TestReportDto> testReportDTOList = new ArrayList<TestReportDto>();
		for(TestReport testReport:testReportList)
		{

			TestReportDto testReportDto = new TestReportDto();
			testReportDto.setDescription(testReport.getDescription());
			testReportDto.setId(testReport.getId());
			testReportDto.setPatient(testReport.getPatient());
			testReportDto.setTest(testReport.getTest());
			testReportDto.setBill(testReport.getBill());
			
			testReportDTOList.add(testReportDto);
		}
		return testReportDTOList;
	}

	public TestReportDto getTestReport(Integer id) {
		return mapper.map(testReportRepository.findById(id).get(),TestReportDto.class);
	}
	
	
	public TestReportDto addTestReport(TestReportDto testReportDto) {
		Test test = testRepository.findById(testReportDto.getTest().getId()).get();
		Patient patient = patientRepository.findById(testReportDto.getPatient().getId()).get();
		
		TestReport testReport = new TestReport();
		testReport.setTest(test);
		testReport.setPatient(patient);
		testReport.setDescription(testReportDto.getDescription());

		Bill newBill = new Bill();
		Bill oldBill = billRepository.findByPatient(patient);
		
		if(oldBill!=null)
		{
			oldBill.setAmount(oldBill.getAmount()+test.getCharge());
			testReport.setBill(oldBill);
			//oldBill.getTestReports().add(testReport);
		}else {
			newBill.setPatient(patient);
			//newBill.getTestReports().add(testReport);
			newBill.setAmount(test.getCharge());
			testReport.setBill(newBill);
		}
		
		testReportRepository.save(testReport);
		return testReportDto;
	}
	
	public TestReportDto updateTestReport(TestReportDto testReportDto) {
		TestReport testReport= testReportRepository.findById(testReportDto.getId()).get();
		return testReportDto;
	}
	
	public void deleteTestReport(Integer id) {
		testReportRepository.deleteById(id);
	}

}
