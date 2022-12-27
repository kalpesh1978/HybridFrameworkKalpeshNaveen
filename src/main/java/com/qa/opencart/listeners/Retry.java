package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0;
	private static int maxTry = 3;
	
	@Override
	public boolean retry(ITestResult iTestResult) {
		if(!iTestResult.isSuccess()) {//check if test not succeed
			if(count < maxTry) {//check if maxTry count is reached
				count++; //Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE); //Mark test as failed
				return true; // Tells TestNG to re-run the test
			}else {
				iTestResult.setStatus(ITestResult.FAILURE); //If maxCount reached, Test marked as failed 
			}
		}else {
			iTestResult.setStatus(ITestResult.SUCCESS); //If Test passes, TestNG marks it as passed
		}
		return false;
	}

}
