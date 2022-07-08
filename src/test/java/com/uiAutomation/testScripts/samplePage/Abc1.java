package com.uiAutomation.testScripts.samplePage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiAutomation.testBase.TestBase;

public class Abc1 extends TestBase {
	int i = 1;
	
	@Test
	public void testLoginAbc1(){
		if(i == 1){
			Assert.assertTrue(true);
		}
		else{
			i++;
			Assert.assertTrue(false);
		}

	}

}
