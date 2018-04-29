package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.RoleDto;
import com.hospitalms.model.Role;
import com.hospitalms.repository.RoleRepository;

@Service("roleService")
public class RoleService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	RoleRepository roleRepository;
	
	public List<RoleDto> getRoles() {
		List<Role> roleList=(List<Role>)roleRepository.findAll();
		List<RoleDto> roleDTOList = new ArrayList<RoleDto>();
		for(Role role:roleList)
		{
			roleDTOList.add(mapper.map(role, RoleDto.class));
		}
		return roleDTOList;
	}
	
	public RoleDto addRole(RoleDto roleDto) {
		Role role= mapper.map(roleDto, Role.class);
		roleRepository.save(role);
		return roleDto;
	}
	
	public RoleDto updateRole(RoleDto roleDto) {
		Role role= roleRepository.findById(roleDto.getId()).get();
		return roleDto;
	}
	
	public void deleteRole(Integer id) {
		roleRepository.deleteById(id);
	}

	public RoleDto getRole(Integer id) {
		return mapper.map(roleRepository.findById(id),RoleDto.class);
	}

	
}
