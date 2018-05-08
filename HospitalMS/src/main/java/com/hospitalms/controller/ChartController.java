package com.hospitalms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalms.dto.DataSet;
import com.hospitalms.model.responses.SingleResponse;
import com.hospitalms.service.ChartService;

@RestController
@RequestMapping("/chart")
public class ChartController {
	
		@Autowired
		private ChartService chartService;

		private static final Logger LOG = LoggerFactory.getLogger(ChartController.class);
		
		/**
		 * Returns a data set for Chart.  
		 * @return
		 */
		@GetMapping(value = "/{type}", produces = { MediaType.APPLICATION_JSON_VALUE })
		public SingleResponse<DataSet> getChartDataSet(@PathVariable("type") String type) {
			SingleResponse<DataSet> resp = new SingleResponse<DataSet>();
			resp.setSuccess(false);
			try {
				resp.setData(chartService.getChartDataSet(type));
				resp.setSuccess(true);
			} catch (Exception e) {

				LOG.error("Exception in getCharts() ", e);
			}
			return resp;
		}

}
