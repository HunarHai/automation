package com.uiAutomation.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.helper.wait.WaitHelper;
import com.uiAutomation.testBase.TestBase;

public class HomePage {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(HomePage.class);
	
	WaitHelper waitHelper;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		
		log.info("HomePage object created...");
		TestBase.logExtentReport("HomePage object created...");
		new TestBase().getNavigationScreen(driver);
	}

}
