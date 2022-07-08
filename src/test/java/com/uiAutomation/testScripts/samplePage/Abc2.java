package com.uiAutomation.testScripts.samplePage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiAutomation.testBase.TestBase;

public class Abc2 extends TestBase {
	int i = 3;
	
	@Test
	public void testLoginAbc2(){
		if(i == 3){
			Assert.assertTrue(true);
		}
		else{
			i++;
			Assert.assertTrue(false);			
		}
	}

}
