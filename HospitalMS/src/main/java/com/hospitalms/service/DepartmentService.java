package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.DepartmentDto;
import com.hospitalms.model.Department;
import com.hospitalms.repository.DepartmentRepository;


@Service("departmentService")
@Transactional
public class DepartmentService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<DepartmentDto> getDepartments() {
		List<Department> departList=(List<Department>)departmentRepository.findAll();
		List<DepartmentDto> departDTOList = new ArrayList<DepartmentDto>();
		for(Department department:departList)
		{
			
			departDTOList.add(mapper.map(department, DepartmentDto.class));
		}
		return departDTOList;
	}

	public DepartmentDto getDepartment(Integer id) {
		return mapper.map(departmentRepository.findById(id).get(), DepartmentDto.class);
	}
	
	
	public DepartmentDto addDepartment(DepartmentDto departmentDto) {
		departmentRepository.save(mapper.map(departmentDto,Department.class));
		return departmentDto;
	}
	
	public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
		Department department= departmentRepository.findById(departmentDto.getId()).get();
		return departmentDto;
	}
	
	public void deleteDepartment(Integer id) {
		departmentRepository.deleteById(id);
	}

}
