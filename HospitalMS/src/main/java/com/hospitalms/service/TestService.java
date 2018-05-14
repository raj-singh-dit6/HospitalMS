package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.TestDto;
import com.hospitalms.model.Test;
import com.hospitalms.repository.TestRepository;


@Service("testService")
@Transactional
public class TestService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(TestService.class);

	@Autowired
	TestRepository testRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<TestDto> getTests() {
		List<Test> testList=(List<Test>)testRepository.findAll();
		List<TestDto> testDtoList = new ArrayList<TestDto>();
		for(Test test:testList)
		{
			testDtoList.add(mapper.map(test, TestDto.class));
		}
		return testDtoList;
	}

	public TestDto getTest(Integer id) {
		return mapper.map(testRepository.findById(id).get(),TestDto.class);
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
