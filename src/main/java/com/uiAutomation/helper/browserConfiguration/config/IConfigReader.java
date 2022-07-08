package com.uiAutomation.helper.browserConfiguration.config;

import com.uiAutomation.helper.browserConfiguration.BrowserType;

/**
 * This interface will have method related to wait feature
 * @author vasudevp
 *
 */
public interface IConfigReader {
	
	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getURL();
	public String getUserName();
	public String getPassword();
	public String getEnvironment();
}
