package com.uiAutomation.testScripts.registrationPage;

//import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiAutomation.helper.assertion.AssertionHelper;
import com.uiAutomation.helper.browserConfiguration.config.ObjectReader;
//import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.pageObject.LoginPage;
import com.uiAutomation.pageObject.MyAccountPage;
import com.uiAutomation.pageObject.RegistrationPage;
import com.uiAutomation.testBase.TestBase;

public class RegistrationTest extends TestBase {
	
//	private static final Logger log = LoggerHelper.getLogger(RegistrationTest.class);
	LoginPage loginPage;
	RegistrationPage registration;
	MyAccountPage myAccountPage;
	
	@Test(description="User registration with valid details")
	public void testRegistrationToApplication(){
		// go to application
		getApplicationURL(ObjectReader.reader.getURL());
		
		// navigate to registration page
		loginPage = new LoginPage(driver);
		loginPage.goToUserRegistrtion();

		// enter user registration data
		registration = new RegistrationPage(driver);
		registration.registerNewUserAccount();
		
		// enter myAccount data
		myAccountPage = new MyAccountPage(driver);
		boolean status = myAccountPage.verifyYourAccountPageMsg();
		
		AssertionHelper.updateTestStatus(status);	
	}

}
