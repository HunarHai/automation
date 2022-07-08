package com.uiAutomation.testScripts.samplePage;

import org.testng.annotations.Test;

import com.uiAutomation.helper.assertion.AssertionHelper;
import com.uiAutomation.testBase.TestBase;

public class Test2 extends TestBase{
	
	@Test
	public void testLogin5(){
		AssertionHelper.makeTrue();
	}

	@Test
	public void testLogin6(){
		AssertionHelper.makeFalse();
	}
	
	@Test
	public void testLogin7(){
		AssertionHelper.makeTrue();
	}

	@Test
	public void testLogin8(){
		AssertionHelper.makeFalse();
	}
}
