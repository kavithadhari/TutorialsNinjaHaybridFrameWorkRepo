package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.Utils.ExtentReporter;
import com.tutorialsninja.qa.Utils.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	
	

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
		
	}
	@Override
	public void onTestStart(ITestResult result)
	{
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+"Stared Executing");	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+"got Successfully Executed");
		}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
	String screenShotPath=Utilities.captureScreenShot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(screenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+" got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	extentTest.log(Status.INFO, result.getThrowable());
	extentTest.log(Status.SKIP, result.getName()+" test got Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		File extentReportPath=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportPath.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
