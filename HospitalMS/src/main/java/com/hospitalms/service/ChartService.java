package com.hospitalms.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.Chart;
import com.hospitalms.dto.ChartData;
import com.hospitalms.dto.DataSet;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Patient;
import com.hospitalms.repository.HospitalRepository;
import com.hospitalms.repository.PatientRepository;

@Service("chartService")
@Transactional
public class ChartService {

	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	ModelMapper mapper;
    	
	
	public DataSet getChartDataSet(String type) {
		DataSet dataSet = new DataSet();
		Chart chart= new Chart();
		
		if(type.equals("HPM"))
		{	
			Calendar cal = Calendar.getInstance();
			chart.setCaption("Hospital-Patients Monthly Report");
			chart.setSubCaption("Total patients admitted in the month of "+new SimpleDateFormat("MMM").format(cal.getTime())+"-"+new SimpleDateFormat("yyyy").format(cal.getTime()));
			chart.setNumberPrefix("");
			chart.setTheme("ocean");
		}
		
		dataSet.setChart(chart);
		
		List<Hospital> hospitals= (List<Hospital>) hospitalRepository.findAll();
		for(Hospital hospital:hospitals)
		{
			ChartData chartData = new ChartData();
			Integer totalPatientsByMonth=0;
			List<Patient> patients=patientRepository.findAllAdmittedPatientsByHospital(hospital);
			for(Patient patient:patients)
			{
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal1.setTime(patient.getAdmittedDate());
				cal2.setTime(new Date());
				if(cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH))
					totalPatientsByMonth++;
			}
			chartData.setLabel(hospital.getName());
			chartData.setValue(totalPatientsByMonth.toString());
			dataSet.getData().add(chartData);
		}	
		return dataSet;
	}

}
