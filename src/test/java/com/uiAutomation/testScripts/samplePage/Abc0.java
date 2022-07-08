package com.uiAutomation.testScripts.samplePage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiAutomation.testBase.TestBase;

public class Abc0 extends TestBase {
	
	@Test
	public void testLoginAbc0(){
		Assert.assertTrue(true);
		
		System.out.println(System.getProperty("url"));
		System.out.println(System.getProperty("userName"));
		System.out.println(System.getProperty("password"));
		System.out.println(System.getProperty("SuiteName"));
		System.out.println(System.getProperty("Environment"));
	}

}
