/*
 * Copyright (c) 2010 by SAP AG,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of SAP AG and its affiliates. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with SAP AG.
 */ 

package com.sap.afw.task.webi;

import org.json.JSONException;
import org.json.JSONObject;

import com.businessobjects.foundation.logging.ILogger;
import com.businessobjects.foundation.logging.LoggerManager;
import com.sap.bong.common.coretask.base.ITaskOutputValue;
import com.sap.bong.task.custom.sdk.CustomTaskImpl;
import com.sap.bong.task.custom.sdk.CustomTaskTemplate;

public class WebiGetDatasetImpl extends CustomTaskImpl {

	private static final ILogger LOG = LoggerManager.getLogger(WebiGetDatasetImpl.class);

	private WebiGetDatasetHelper helper;

	public WebiGetDatasetImpl(CustomTaskTemplate taskTemplate) {
		super(taskTemplate);
	}


	@Override
	public TASK_STATUS execute() {
		
		try {
			helper = new WebiGetDatasetHelper(this, false);
			return helper.workOnReportTable();
		} catch (Exception e) {
			LOG.error(e);
			return TASK_STATUS.failure;
		}
	}

	@Override
	public JSONObject inputValueInstance() throws JSONException {
		return new WebiGetDatasetInputValue(this);
	}


	@Override
	public ITaskOutputValue outputValue() {
		return new ITaskOutputValue() {

			@Override
			public String getValue(String key) {
				if (key.startsWith("csv_output")) {
					return helper.getCsvOutput();
				}
				return null;
			}
		};
	}

	@Override
	public String resultSummary() {
		return helper.getResultSummary();
	}

	@Override
	public String resultDetails() {
		// use CR LF to start new line
		//return "col1,col2,col3\r\n,v11,v12,v13\r\n,v21,v22,v23";
		return helper.getCsvOutput(); //resultDetails;
	}
}

