package com.uiAutomation.helper.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.uiAutomation.helper.logger.LoggerHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class Retry implements IRetryAnalyzer{

	private static final Logger log = LoggerHelper.getLogger(Retry.class);
	private int retryCount = 0;
	private int maxRetryCount = 3;
		
	public boolean retry(ITestResult arg0) {
		if(retryCount<maxRetryCount){
			log.info("Retrying to test " +arg0.getName()+ " with Status " + getResultStatusName(arg0.getStatus()) + " for the " + (retryCount+1)+ " time(s).");
			retryCount++;
			return true;
		}
		return false;
	}
	
	public String getResultStatusName(int status){
		String resultName = null;
		if(status == 1){
			resultName = "SUCCESS";
		}
		if(status == 2){
			resultName = "FAILURE";
		}
		if(status == 3){
			resultName = "SKIP";
		}
		return resultName;
	}

}
