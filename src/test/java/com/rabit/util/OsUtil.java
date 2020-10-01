/**
 * 
 */
package com.rabit.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author sampath.c
 *
 */
public class OsUtil {

	private static final String OSNAME = System.getProperty("os.name").toLowerCase();

	private final Properties variables = new Properties();

	private static final OsUtil OS_UTIL = new OsUtil();

	private OsUtil(){
		parse();
	}

	public static OsUtil getInstance(){
		return OS_UTIL;
	}

	public static boolean isWindows() {
		return OSNAME.indexOf("win") >= 0;
	}

	public static boolean isMac() {
		return OSNAME.indexOf("mac") >= 0;
	}

	public static boolean isUnix() {
		return OSNAME.indexOf("nix") >= 0 || OSNAME.indexOf("nux") >= 0 || OSNAME.indexOf("aix") > 0;
	}

	public static boolean isSolaris() {
		return OSNAME.indexOf("sunos") >= 0;
	}

	public static String getOsName() {
		String osName;
		if (isWindows()) {
			osName = "windows";
		} else if (isMac()) {
			osName = "mac";
		} else if (isUnix()) {
			osName = "linux";
		} else if (isSolaris()) {
			osName = "solaris";
		} else {
			osName = "Your OS is not support!!";
		}
		return osName;
	}

	public static String getArchitecture(){
		return System.getProperty("sun.arch.data.model");
	}

	public static String getHostName() {
		String hostName = "localhost";
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			hostName = inetAddress.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostName;
	}

	private void parse() {

		final String command;

		if (isWindows9x(OSNAME)) {
			command = "command.com /c set";
		} else if ((OSNAME.indexOf("nt") > -1) || (OSNAME.indexOf("windows") > -1)
				|| (OSNAME.indexOf("os/2") > -1)) {
			command = "cmd.exe /c set";
		} else {
			// should work for just about any Unix variant
			command = "env";
		}

		// Get our environment
		try {
			final Process p = Runtime.getRuntime().exec(command);
			p.getOutputStream().close();

			// Capture the output of the command
			final BufferedReader stdoutStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
			final BufferedReader stderrStream = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			try {
				// Parse the output
				String line;
				String key = null;
				while ((line = stdoutStream.readLine()) != null) {
					int idx = line.indexOf('=');
					String value;
					if (idx == -1) {
						if (key == null) {
							continue;
						}
						// potential multi-line property. Let's rebuild it
						value = variables.getProperty(key);
						value += "\n" + line;
					} else {
						key = line.substring(0, idx);
						value = line.substring(idx + 1);
					}
					variables.setProperty(key, value);
				}
			} finally {
				// Close down our streams
				stdoutStream.close();
				stderrStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isWindows9x(String os) {
		return os.indexOf("windows 9") > -1;
	}

	public String getVariable(String variable) {
		return variables.getProperty(variable);
	}

	public String getVariable(String variable, String defaultValue) {
		return variables.getProperty(variable, defaultValue);
	}

	public String getVariableIgnoreCase(final String variable) {
		final Enumeration<Object> keys = variables.keys();
		while (keys.hasMoreElements()) {
			final String key = (String) keys.nextElement();
			if (key.equalsIgnoreCase(variable)) {
				return variables.getProperty(key);
			}
		}
		return null;
	}

	public void add(String variable, String value) {
		variables.setProperty(variable, value);
	}

	public void del(final String variable) {
		variables.remove(variable);
	}

	public List<String> getEnvironment() {
		final List<String> env = new ArrayList<String>();
		Enumeration<Object> keys = variables.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			env.add(key + "=" + variables.getProperty(key));
		}
		return env;
	}

}
