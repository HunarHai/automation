package com.uiAutomation.testScripts.samplePage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiAutomation.testBase.TestBase;

public class App extends TestBase {
	@Test
	public void mainAbc() {
		System.out.println("Hello World!");
		Assert.assertTrue(true);
	}
}
