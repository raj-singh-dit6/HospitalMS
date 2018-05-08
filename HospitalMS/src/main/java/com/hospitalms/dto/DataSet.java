package com.hospitalms.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DataSet {

	private Chart chart;
	private List<ChartData> data= new ArrayList<ChartData>();
}
