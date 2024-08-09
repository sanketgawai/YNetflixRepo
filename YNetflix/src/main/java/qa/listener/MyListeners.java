package qa.listener;

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

import qa.util.ExtentReporter;

public class MyListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		
		try {
			//ExtentReports extentReport = ExtentReporter.generateExtentReport();
			extentReport = ExtentReporter.generateExtentReport();
		}catch(Throwable e) {
			e.printStackTrace();
	}
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+"started execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TakesScreenshot t = (TakesScreenshot)driver;  
		File src = t.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+"got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"got skipped");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();//***** if don't flush all details not added to report
		
		//***** below code for auto logic for extent report it will display report after test over 
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);//path of extent report 
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
