/*
 * Copyright (c) 2010 by SAP AG,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of SAP AG and its affiliates. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with SAP AG.
 */ 

package com.sap.afw.task;

import com.sap.afw.task.sample.JavaScriptTemplate;
import com.sap.bong.task.custom.sdk.CustomTaskPluginActivator;

public class Activator extends CustomTaskPluginActivator {

	@Override
	public void registerPluginTaskTemplates() throws Exception {
		registerTaskTemplate(new JavaScriptTemplate());
	}
}
