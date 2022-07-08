package com.uiAutomation.helper.browserConfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.uiAutomation.helper.browserConfiguration.BrowserType;
import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.helper.resource.ResourceHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class PropertyReader implements IConfigReader{

	private static FileInputStream file;
	public static Properties OR;
	private static final Logger log = LoggerHelper.getLogger(PropertyReader.class);
	
	public PropertyReader() {
		log.info("PropertyReader() started.");
		try {
			String filePath = ResourceHelper
					.getResourcePath("/src/main/resources/configFile/config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);
		} catch (Exception e) {
			log.error("PropertyReader(): " + e.getMessage()); 
			e.printStackTrace();
		}
		log.info("PropertyReader() finished.");
	}

	@Override
	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("implicitWait"));
	}

	@Override
	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitWait"));
	}

	@Override
	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageLoadTime"));
	}

	@Override
	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}
	
	@Override
	public String getURL() {
		if(System.getProperty("url")!= null){
			return System.getProperty("url");
		}
		return OR.getProperty("applicationURL");
	}

	@Override
	public String getUserName() {
		if(System.getProperty("userName")!= null){
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	@Override
	public String getPassword() {
		if(System.getProperty("password")!= null){
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}

	@Override
	public String getEnvironment() {
		if(System.getProperty("environment")!= null){
			return System.getProperty("environment");
		}
		return OR.getProperty("environment");
	}

}
