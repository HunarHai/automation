package com.uiAutomation.testScripts.samplePage;

import org.testng.annotations.Test;

import com.uiAutomation.testBase.TestBase;

public class TestScreenshot extends TestBase{

	@Test
	public void testScreen(){
		driver.get("http://www.google.co.in/");
		captureScreenshot("Google Home", driver);
	}
}
