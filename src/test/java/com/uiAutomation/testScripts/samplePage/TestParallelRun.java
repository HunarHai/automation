package com.uiAutomation.testScripts.samplePage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.pageObject.LoginPage;
import com.uiAutomation.testBase.TestBase;

public class TestParallelRun extends TestBase{

	private static final Logger log = LoggerHelper.getLogger(LoginPage.class);

	@Test
	public void ChromeTest1(){
		log.info("The thread ID for Chrome1 is "+ Thread.currentThread().getId());
		driver.get("http://www.google.co.in/");
		captureScreenshot("Google1 Home", driver);
	//	driver.findElement(By.xpath("//input[@name='btnI']")).click();
		log.info("Chrome1 shows page title as "+ driver.getTitle());
		/*
		 * try { Thread.sleep(3000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}
	
	@Test
	public void ChromeTest2(){
		log.info("The thread ID for Chrome2 is "+ Thread.currentThread().getId());
		driver.get("http://www.google.co.in/");
		captureScreenshot("Google2 Home", driver);
	//	driver.findElement(By.xpath("//input[@name='btnI']")).click();
		log.info("Chrome2 shows page title as "+ driver.getTitle());
		/*
		 * try { Thread.sleep(3000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}
	
	@Test
	public void ChromeTest3(){
		log.info("The thread ID for Chrome3 is "+ Thread.currentThread().getId());
		driver.get("http://www.google.co.in/");
		captureScreenshot("Google3 Home", driver);
	//	driver.findElement(By.xpath("//input[@name='btnI']")).click();
		log.info("Chrome3 shows page title as "+ driver.getTitle());
		/*
		 * try { Thread.sleep(3000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}
	
	@Test
	public void ChromeTest4(){
		log.info("The thread ID for Chrome4 is "+ Thread.currentThread().getId());
		driver.get("http://www.google.co.in/");
		captureScreenshot("Google4 Home", driver);
	//	driver.findElement(By.xpath("//input[@name='btnI']")).click();
		log.info("Chrome4 shows page title as "+ driver.getTitle());
	}
}