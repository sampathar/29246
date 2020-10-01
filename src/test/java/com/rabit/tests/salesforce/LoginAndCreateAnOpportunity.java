/**
 * 
 */
package com.rabit.tests.salesforce;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.rabit.util.SuperTestNG;

/**
 * @author sampath.c
 *
 */
public class LoginAndCreateAnOpportunity extends SuperTestNG {
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 10;
	
	@Test()
	public void testOpportunities() throws InterruptedException {
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
	    driver.findElement(By.xpath("//a[@title='Opportunities']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@title='New']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[contains(text(),'Opportunity Name')]/../..//*[@class=' input'][@type='text']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[contains(text(),'Opportunity Name')]/../..//*[@class=' input'][@type='text']")).clear();
	    Thread.sleep(2000);
	    String Opportunity= "Opportunity " + generateRandomString();
	    driver.findElement(By.xpath("//span[contains(text(),'Opportunity Name')]/../..//*[@class=' input'][@type='text']")).sendKeys(Opportunity);
	    Thread.sleep(2000);
	    DateFormat df = new SimpleDateFormat("dd");
	    Date dateobj = new Date();
	    String dateToString = df.format(dateobj); 
	    System.out.println(dateToString);
	    driver.findElement(By.xpath("//label[@class='label inputLabel uiLabel-left form-element__label uiLabel']//span[contains(text(),'Close Date')]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//td[@class='slds-is-selected uiDayInMonthCell']")).click();
	    //driver.findElement(By.xpath("//span[contains(text(),'"+dateToString+"')]")).click();;
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[contains(text(),'Stage')]/../..//a[@class='select']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Prospecting")).click();
	    Thread.sleep(2000);  
	    driver.findElement(By.xpath("//button[@title='Save']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[6]/div/div/div/button/lightning-primitive-icon")).click();
	    driver.navigate().refresh();
	    Thread.sleep(20000);
	    WebElement element = driver.findElement(By.cssSelector("a#detailTab__item"));
	    JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("arguments[0].click();", element);
	   // driver.findElement(By.xpath("//a[@id='detailTab__item']")).submit();
	    //driver.findElement(By.id("detailTab__item")).click();
	    Thread.sleep(2000);
	   String ActualOpportunityName= driver.findElement(By.xpath("//span[contains(text(),'Opportunity Name')]/../..//lightning-formatted-text")).getText();
	   System.out.println(ActualOpportunityName);
	   if(ActualOpportunityName.equalsIgnoreCase(Opportunity))
	   {
		   System.out.println("Opportunity Created Successfully");
	   }
	   else
	   {
		   System.out.println("Opportunity NOT Created Successfully");
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
