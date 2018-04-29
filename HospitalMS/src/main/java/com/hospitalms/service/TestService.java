package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.TestDto;
import com.hospitalms.model.Test;
import com.hospitalms.repository.TestRepository;


@Service("testService")
public class TestService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(TestService.class);

	@Autowired
	TestRepository testRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<TestDto> getTests() {
		List<Test> hospList=(List<Test>)testRepository.findAll();
		List<TestDto> hospDTOList = new ArrayList<TestDto>();
		for(Test test:hospList)
		{
			hospDTOList.add(mapper.map(test, TestDto.class));
		}
		return hospDTOList;
	}

	public TestDto getTest(Integer id) {
		return mapper.map(testRepository.findById(id),TestDto.class);
	}
	
	
	public TestDto addTest(TestDto testDto) {
		testRepository.save(mapper.map(testDto,Test.class));
		return testDto;
	}
	
	public TestDto updateTest(TestDto testDto) {
		Test test= testRepository.findById(testDto.getId()).get();
		return testDto;
	}
	
	public void deleteTest(Integer id) {
		testRepository.deleteById(id);
	}

}
