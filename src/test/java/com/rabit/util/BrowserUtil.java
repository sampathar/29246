/**
 * 
 */
package com.rabit.util;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author sampath.c
 *
 */
public final class BrowserUtil {
	
	private BrowserUtil(){}
	
	private static final BrowserUtil browserUtil = new BrowserUtil();
	
	public static BrowserUtil getInstance() {
		return browserUtil;
	}
	
	private WebDriver webDriver;

	private static final String OS_VERSION = OsUtil.getArchitecture();

	private static final String OS_NAME = OsUtil.getOsName();

	private String getFirefoxDriverPath() {
		String driverPath = "";
		if(OS_NAME.equalsIgnoreCase("linux")){
			if(OS_VERSION.equals("32")) {
				driverPath= FilePaths.get32LinuxFirefoxWebDriverPath();
//				"D:\\VC_checkouts\\git\\selenium_maven\\src\\test\\resources\\webdrivers\\firefox\\linux\\32\\geckodriver"
			} else {
				driverPath = FilePaths.get64LinuxFirefoxWebDriverPath();
			}
		}else{
			driverPath = FilePaths.getWindowsFirefoxDriverPath();
		}
		File driverFile = new File(driverPath);
		driverFile.setExecutable(true);
		System.out.println(driverFile);
		return driverPath;
	}

	private String getChromeDriverPath() {
		String driverPath = "";
		if(OS_NAME.equalsIgnoreCase("linux")){
			if(OS_VERSION.equals("32")) {
				driverPath= FilePaths.get32LinuxChromeWebDriverPath();
			} else {
				driverPath = FilePaths.get64LinuxChromeWebDriverPath();
			}
		} else{
			driverPath = FilePaths.getWindowsChromeDriverPath();
		}
		File driverFile = new File(driverPath);
		driverFile.setExecutable(true);
		System.out.println(driverFile);
		return driverPath;
	}

	public WebDriver getFirefoxWebDriver() {
System.setProperty("webdriver.gecko.driver", getFirefoxDriverPath());
		
		FirefoxProfile profile = new FirefoxProfile();
		
		profile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");
		profile.setPreference("browser.download.dir", File.separator);
		profile.setPreference("browser.download.folderList", 2);
		
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		
		profile.setPreference("browser.download.manager.showWhenStarting", false );
		profile.setPreference("pdfjs.disabled", true );
		
		
		profile.setPreference("permissions.default.desktop-notification", 1);
	    DesiredCapabilities capabilities=DesiredCapabilities.firefox();
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		FirefoxOptions firefoxOptions=new FirefoxOptions();
	
		firefoxOptions.addArguments("--headless");
		firefoxOptions.addArguments("--disable-notifications");	
		firefoxOptions.addArguments("--disable-gpu");		
		firefoxOptions.addArguments("--test-type");
		//System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		webDriver = new FirefoxDriver(firefoxOptions);
		return webDriver;
	}
	
	public WebDriver getChromeWebDriver() {
		System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
		WebDriver webDriver= new ChromeDriver(new ChromeDriverService.Builder().usingPort(65530).build());
		
		Map<String, Object> chromePreferences = new Hashtable<String, Object>();
		chromePreferences.put("profile.default_content_settings.popups", 0);
		chromePreferences.put("download.prompt_for_download", "false");
		
		chromePreferences.put("download.default_directory", File.separator);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-gpu");
		options.addArguments("--headless");
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		options.setExperimentalOption("prefs", chromePreferences);
		webDriver = new ChromeDriver(options);
		return webDriver;
		
		
	}

}
