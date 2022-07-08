package com.uiAutomation.testScripts.loginPage;

//import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiAutomation.helper.assertion.AssertionHelper;
import com.uiAutomation.helper.browserConfiguration.config.ObjectReader;
//import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.pageObject.LoginPage;
import com.uiAutomation.testBase.TestBase;

public class LoginTest extends TestBase{
	
//	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	@Test(description="Login test with valid credentials")
	public void testLoginToApplication(){
		getApplicationURL(ObjectReader.reader.getURL());
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		
		boolean status = loginPage.verifyLoginSuccessMsg();
		AssertionHelper.updateTestStatus(status);
	}
}
