package com.uiAutomation.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uiAutomation.helper.resource.ResourceHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class LoggerHelper {

	private static boolean root = false;

	// private final static Logger log = Logger.getLogger(LoggerHelper.class);

	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class cls) {
		if (root) {
			return Logger.getLogger(cls);
		}

		PropertyConfigurator
				.configure(ResourceHelper
						.getResourcePath("/src/main/resources/configFile/log4j.properties"));
		root = true;
		return Logger.getLogger(cls);
	}

	/*
	 * private static void init() {
	 * PropertyConfigurator.configure(ResourceHelper
	 * .getResourcePath("/src/main/resources/configFile/log4j.properties")); }
	 */

	/*
	 * public static void main(String[] args) { // init(); // Set up a simple
	 * configuration that logs on the console //BasicConfigurator.configure();
	 * Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	 * log.info("Logger is configured."); log.info("Entering the application.");
	 * log.info("Exiting the application."); }
	 */
}
