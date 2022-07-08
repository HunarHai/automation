package com.uiAutomation.helper.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.uiAutomation.helper.logger.LoggerHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class AssertionHelper {
	
	/**
	 * Logger object has to be a 'static'. 
	 * Inside the static method, non-static logger can not be called.
	 */
	private static final Logger log = LoggerHelper.getLogger(AssertionHelper.class);	
	
	/**
	 * This method asserts that two Strings are equal or not
	 * @param s1
	 * @param s2
	 */
	public static void verifyText(String s1, String s2){
		log.info("Verifying text: " + s1 + " with " +s2);
		Assert.assertEquals(s1,s2);
	}
	
	/**
	 * This method asserts that a condition is true. 
	 * If not, an AssertionError is thrown.
	 */
	public static void makeTrue(){
		log.info("Making the script PASS...");
		Assert.assertTrue(true);
	}
	
	/**
	 * This method asserts that a condition is true. 
	 * If not, an AssertionError, with the given message, is thrown.
	 * @param message
	 */
	public static void makeTrue(String message){
		log.info("Making the script PASS..."+message);
		Assert.assertTrue(true, message);
	}
	
	/**
	 * This method asserts that a condition is false.
	 *  If not, an AssertionError is thrown.
	 */
	public static void makeFalse(){
		log.info("Making the script FAIL...");
		Assert.assertTrue(false);
	}
	
	/**
	 * This method asserts that a condition is false.
	 * If not, an AssertionError, with the given message, is thrown.
	 * @param message
	 */
	public static void makeFalse(String message){
		log.info("Making the script FAIL..." +message);
		Assert.assertTrue(false, message);
	}
	
	/**
	 * This method to help in verifying the another method is returning 'True'
	 * @param status
	 */
	public static void verifyTrue(boolean status){
		Assert.assertTrue(status);
	}
	
	/**
	 * This method to help in verifying the another method is returning 'False'
	 * @param status
	 */
	public static void verifyFalse(boolean status){
		Assert.assertFalse(status);
	}
	
	/**
	 * This method asserts that an object is null. If it is not, an AssertionError is thrown.
	 * @param s1
	 */
	public static void verifyNull(String s1){
		log.info("Verifying object is null...");
		Assert.assertNull(s1);
	}
	
	/**
	 * This method asserts that an object is not null. If it is, an AssertionError is thrown.
	 * @param s1
	 */
	public static void verifyNotNull(String s1){
		log.info("Verifying the object is not null...");
		Assert.assertNotNull(s1);
	}
	
	/**
	 * 
	 */
	public static void pass(){
		Assert.assertTrue(true);
	}
	
	/**
	 * 
	 */
	public static void fail(){
		Assert.assertTrue(false);
	}
	
	/**
	 * 
	 * @param status
	 */
	public static void updateTestStatus(boolean status){
		if(status){
			pass();
		}
		else {
			fail();
		}
	}
	
}
