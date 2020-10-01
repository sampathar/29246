/**
 * 
 */
package com.rabit.util;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


/**
 * @author sampath.c
 *
 */
public class SuperTestNG {

	protected WebDriver driver;
	private BrowserUtil browserUtil = BrowserUtil.getInstance();
	public static ExtentTest extentTest;//logger
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	
	@Parameters("browser")
	
	@BeforeMethod
	public void openBrowser(@Optional String browser, Method method) {
		String url = "https://login.salesforce.com/";
		if(browser == null) {
			browser = BrowserType.firefox.getType();
		}
		initialiseWebDriver(browser);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	@BeforeSuite
	public void setExtent() {
	}
	
	@AfterSuite
	public void endReport() {
		
	}

	private void initialiseWebDriver(String browser) {
		switch(BrowserType.valueOf(browser.toLowerCase())) {
		case firefox: {
			System.out.println("Selected browser firefox");
			driver = browserUtil.getFirefoxWebDriver();
			break;
		}
		case chrome: {
			System.out.println("Selected browser chrome");
			driver = browserUtil.getChromeWebDriver();
			break;
		}
		default: {
			System.out.println("Selected invalid browser: " + browser.toLowerCase() + " so selecting by default browser i.e. firefox");
			driver = browserUtil.getFirefoxWebDriver();
		}
		}
	}

	@AfterMethod
	public void closeBrowser(ITestResult testResult){
		driver.close();
	}
	
	public static String takeScreenShot(WebDriver driver, String methodName) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File screenShotDir = new File(FilePaths.getScreenShotsDirPath());
		if(!screenShotDir.exists()) {
			screenShotDir.mkdirs();
		}
		String destPath = screenShotDir + File.separator + methodName + "-" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".png";
		try {
			FileUtils.copyFile(src, new File(destPath));
		} catch (Exception e) {
			extentTest.log(Status.FAIL, e.getMessage());
		}
		return destPath;
	}

}
