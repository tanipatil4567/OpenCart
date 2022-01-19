package com.psl.OpenCart.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.psl.OpenCart.commons.LoggersExample;
import com.psl.OpenCart.pages.EditAccountInfo;




public class TestEditAccountInfo {

	
	 WebDriver driver;
	 EditAccountInfo eaiPage;
	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/Opencart");
		LoggersExample.logger.info("Browser opened successfully!!!");
		try {
			
			Properties p=new Properties();
			FileInputStream fis=new FileInputStream("LoginDetails.properties");
			p.load(fis);
			FileOutputStream fos=new FileOutputStream("LoginDetails.properties");
			p.store(fos,null);
			
			driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
			Thread.sleep(2000);

			WebElement elemnt= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
			Actions a=new Actions(driver);
			a.moveToElement(elemnt).click().perform();
			Thread.sleep(2000);

			driver.findElement(By.name("email")).sendKeys(p.getProperty("E-Mail"));
			driver.findElement(By.name("password")).sendKeys(p.getProperty("password"));
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
			Thread.sleep(2000);
			}
			catch(IOException e){
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		eaiPage=new EditAccountInfo(driver);
	}
	
	@Test (priority=1)
	public void validEditAccountInformation() throws IOException, InterruptedException
	{
		eaiPage.validEditAccount(driver);
	}
	
	@Test (priority=2)
	public void InvalidEditAccountInformation() throws IOException, InterruptedException
	{
		eaiPage.InvalidEditAccount(driver);
	}
	
	
	@AfterTest
	public void closeBrowsers()
	{
		driver.close();
	}

}
