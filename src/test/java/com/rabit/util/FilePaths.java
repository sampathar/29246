/**
 * 
 */
package com.rabit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sampath.c
 *
 */
public final class FilePaths {

	private static String getChromeWebDriverFolderPath() {
		return Constants.WEBDRIVERS_FOLDER_PATH + File.separator + FixedNames.CHROME_FOLDER_NAME;
	}

	private static String getFirefoxWebDriverFolderPath() {
		return Constants.WEBDRIVERS_FOLDER_PATH + File.separator + FixedNames.FIREFOX_FOLDER_NAME;
	}

	private static String getChromeLinuxFolder() {
		return getChromeWebDriverFolderPath() + File.separator + FixedNames.LINUX_FOLDER_NAME;
	}

	private static String getChromeWindowsFolder() {
		return getChromeWebDriverFolderPath() + File.separator + FixedNames.WINDOWS_FOLDER_NAME;
	}

	private static String getFirefoxLinuxFolder() {
		return getFirefoxWebDriverFolderPath() + File.separator + FixedNames.LINUX_FOLDER_NAME;
	}

	private static String getFirefoxWindowsFolder() {
		return getFirefoxWebDriverFolderPath() + File.separator + FixedNames.WINDOWS_FOLDER_NAME;
	}

	public static String get32LinuxChromeWebDriverPath() {
		return getChromeLinuxFolder() + File.separator + FixedNames.ARC32_FOLDER_NAME + File.separator + FixedNames.LINUX_CHROME_DRIVER_NAME;
	}

	public static String get64LinuxChromeWebDriverPath() {
		return getChromeLinuxFolder() + File.separator + FixedNames.ARC64_FOLDER_NAME + File.separator + FixedNames.LINUX_CHROME_DRIVER_NAME;
	}

	public static String get32LinuxFirefoxWebDriverPath() {
		return getFirefoxLinuxFolder() + File.separator + FixedNames.ARC32_FOLDER_NAME + File.separator + FixedNames.LINUX_FIREFOX_DRIVER_NAME;
	}

	public static String get64LinuxFirefoxWebDriverPath() {
		return getFirefoxLinuxFolder() + File.separator + FixedNames.ARC64_FOLDER_NAME + File.separator + FixedNames.LINUX_FIREFOX_DRIVER_NAME;
	}

	public static String getWindowsChromeDriverPath() {
		return getChromeWindowsFolder() + File.separator + FixedNames.WINDOWS_CHROME_DRIVER_NAME;
	}

	public static String getWindowsFirefoxDriverPath() {
		return getFirefoxWindowsFolder() + File.separator + FixedNames.WINDOWS_FIREFOX_DRIVER_NAME;
	}
	
	public static String getTestOutputDirPath() {
		return Constants.PROJECT_FOLDER_PATH + File.separator + FixedNames.TEST_OUTPUT_FOLDER_NAME;
	}
	
	public static String getScreenShotsDirPath() {
		return getTestOutputDirPath() + File.separator + FixedNames.SCREEN_SHOT_FOLDER_NAME;
	}
	
	public static String getExtentReportPath() {
		return getTestOutputDirPath() + File.separator + "extentreport-" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".html";
	}
	
}
