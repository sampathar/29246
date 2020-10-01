/**
 * 
 */
package com.rabit.tests.salesforce;

import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.rabit.util.SuperTestNG;

/**
 * @author sampath.c
 *
 */
public class LoginAndCreateAnAccount extends SuperTestNG {
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 10;
	
	@Test()
	public void testAccount() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='username_container']/input")).clear();
	    driver.findElement(By.xpath("//div[@id='username_container']/input")).sendKeys("autorabit@testing.com");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//form[@id='login_form']/input")).clear();
	    driver.findElement(By.xpath("//form[@id='login_form']/input")).sendKeys("Brillio@2020");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//form[@id='login_form']/input[2]")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@title='Accounts']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@title='New']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[contains(text(),'Account Name')]/../..//*[@class=' input'][@type='text']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[contains(text(),'Account Name')]/../..//*[@class=' input'][@type='text']")).clear();
	    Thread.sleep(2000);
	    String Account= "Account " + generateRandomString();
	    driver.findElement(By.xpath("//span[contains(text(),'Account Name')]/../..//*[@class=' input'][@type='text']")).sendKeys(Account);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//button[@title='Save']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[6]/div/div/div/button/lightning-primitive-icon")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("(//a[contains(text(),'Details')])[2]")).click();
	    Thread.sleep(2000);
	   String ActualAccountName= driver.findElement(By.xpath("//span[contains(text(),'Account Name')]/../..//lightning-formatted-text")).getText();
	   System.out.println(ActualAccountName);
	   if(ActualAccountName.equalsIgnoreCase(Account))
	   {
		   System.out.println("Account Created Successfully");
	   }
	   else
	   {
		   System.out.println("Account NOT Created Successfully");
	   }
	   
	}
	
	/**
	 * This method generates random string
	 * 
	 * @return int
	 */
		
	public String generateRandomString() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	/**
	 * This method generates random numbers
	 * 
	 * @return int
	 */
	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
}
}
