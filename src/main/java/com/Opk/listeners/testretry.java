package com.Opk.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class testretry implements IRetryAnalyzer
{	private int count = 0;
	private static int maxtry = 3;
	
	public boolean retry(ITestResult result) 
	{
		if (!result.isSuccess())
		{
			if (count < maxtry)
			{
				count ++;
				result.setStatus(ITestResult.FAILURE);
				return true;
			}
			else
			{
				result.setStatus(ITestResult.FAILURE);
			}
		}
		else
		{
			result.setStatus(ITestResult.SUCCESS);
		}
			
		return false;
	}
	
	
	

}
