package com.uiAutomation.testScripts.samplePage;

import org.testng.annotations.Test;

import com.uiAutomation.helper.assertion.AssertionHelper;
import com.uiAutomation.testBase.TestBase;

public class Test1 extends TestBase{
	
	@Test
	public void testLogin1(){
		AssertionHelper.makeTrue();
	}

	@Test
	public void testLogin2(){
		AssertionHelper.makeFalse();
	}
	
	@Test
	public void testLogin3(){
		AssertionHelper.makeTrue();
	}

	@Test
	public void testLogin4(){
		AssertionHelper.makeFalse();
	}
}
