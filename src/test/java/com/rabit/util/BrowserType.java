/**
 * 
 */
package com.rabit.util;

/**
 * @author sampath.c
 *
 */
public enum BrowserType {
	
	firefox("firefox"), chrome("chrome"), iexplorer("iexplorer");
	
	private String type;
	
	BrowserType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

}
