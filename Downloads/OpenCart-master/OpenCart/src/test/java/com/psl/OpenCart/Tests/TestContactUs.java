package com.psl.OpenCart.Tests;

import java.io.IOException;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.psl.OpenCart.commons.LoggersExample;
import com.psl.OpenCart.pages.ContactUs;



/**
 * @author bandaru_ramya
 *
 */
public class TestContactUs {
	 WebDriver driver;
	 ContactUs cuPage;
	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/Opencart");
		LoggersExample.logger.info("Browser opened successfully!!!");
//		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
//		driver=new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost/opencartpro/");

		cuPage=new ContactUs(driver);
	}
	
	@Test (priority=1)
	public void validContactform() throws IOException, InterruptedException
	{
		cuPage.validContactform(driver);
	}
	
	@Test (priority=2)
	public void InvalidContactform() throws IOException, InterruptedException
	{
		cuPage.InvalidContactform(driver);
	}
	
	
	@AfterTest
	public void closeBrowsers()
	{
		driver.close();
	}
	
}
