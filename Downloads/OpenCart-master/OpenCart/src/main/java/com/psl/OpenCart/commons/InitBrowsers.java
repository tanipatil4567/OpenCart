package com.psl.OpenCart.commons;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.psl.OpenCart.commons.LoggersExample;
public class InitBrowsers {
	static WebDriver driver=null;
	public void Initialize_browser() {
	
	BasicConfigurator.configure();
	try
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver= new ChromeDriver();
			
		driver.manage().window().maximize();
		// Navigate to the demoqa website
		driver.get("http://localhost/opencartpro/");
		Thread.sleep(5000);
		LoggersExample.logger.info("Opened OpenCart Application successfully");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		driver.close();
	}
	}
	
}
