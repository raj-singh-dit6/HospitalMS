package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.BillStatusDto;
import com.hospitalms.model.BillStatus;
import com.hospitalms.repository.BillStatusRepository;

@Service("billStatusService")
@Transactional
public class BillStatusService {

	private static final Logger LOG = LoggerFactory.getLogger(BillStatusService.class);

	@Autowired
	BillStatusRepository billStatusRepository;

	@Autowired
	ModelMapper mapper;

	public List<BillStatusDto> getBillStatuses() {
		List<BillStatus> hospList = (List<BillStatus>) billStatusRepository.findAll();
		List<BillStatusDto> hospDTOList = new ArrayList<BillStatusDto>();
		for (BillStatus billStatus : hospList) {
			hospDTOList.add(mapper.map(billStatus, BillStatusDto.class));
		}
		return hospDTOList;
	}

	public BillStatusDto getBillStatus(Integer id) {
		return mapper.map(billStatusRepository.findById(id).get(), BillStatusDto.class);
	}

	public BillStatusDto addBillStatus(BillStatusDto billStatusDto) {
		billStatusRepository.save(mapper.map(billStatusDto, BillStatus.class));
		return billStatusDto;
	}

	public BillStatusDto updateBillStatus(BillStatusDto billStatusDto) {
		BillStatus billStatus = billStatusRepository.findById(billStatusDto.getId()).get();
		return billStatusDto;
	}

	public void deleteBillStatus(Integer id) {
		billStatusRepository.deleteById(id);
	}

}
