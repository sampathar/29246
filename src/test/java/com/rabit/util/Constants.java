/**
 * 
 */
package com.rabit.util;

import java.io.File;

/**
 * @author sampath.c
 *
 */
public class Constants {
	
	public static final String RES_FOLDER_PATH = Constants.class.getResource("/").getPath();
	public static final String WEBDRIVERS_FOLDER_PATH = RES_FOLDER_PATH + File.separator + FixedNames.WEBDRIVERS_FOLDER_NAME;
	public static final String PROJECT_FOLDER_PATH = System.getProperty("user.dir");
	
}
