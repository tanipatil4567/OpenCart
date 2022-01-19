package com.psl.OpenCart.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class PhonesAndPDAs {
	
	
		
		 WebDriver driver;
		   
		   @BeforeTest  // annotation
		   public void setUp()
		   {
			   System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
			     driver=new ChromeDriver();
			     driver.get("http://localhost/Opencart");
			     driver.manage().window().maximize();
			     driver.manage().deleteAllCookies();
			     System.out.println("Chrome opened Sucessfully");
		   }
		   

		@Test(priority = 1)
		   public void verifyPhonesAndPDAsModuleInHomePage() throws InterruptedException
		   {
			// Checking whether the PhonesAndPDAs module is present in the Home page
			   boolean flag= driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[4]/a")).isDisplayed();
			   Assert.assertTrue(flag);
			   Thread.sleep(2000);
			   System.out.println("PhonesAndPDAs module is present in the Home page");
		   }
		
		@Test(priority = 2)
		public void verifyPhonesAndPDAsIsClickable() throws InterruptedException
		{
			 //Clicking on the PhonesAndPDAs module
			   driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[6]/a")).click();
			   Thread.sleep(2000);
		}
		
		@Test(priority = 3)
		// Adding HTC to the cart
		public void addingToCart() throws InterruptedException
		{
			   verifyPhonesAndPDAsIsClickable();
			   //clicking on the list option
			   driver.findElement(By.xpath("//*[@id=\"list-view\"]/i")).click();
			   Thread.sleep(2000);
			   
			   //Clicking on the product
			   driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[1]/a/img")).click();
			   Thread.sleep(2000);
			   
			   //Scrolling
			   JavascriptExecutor js=(JavascriptExecutor)driver;
			   js.executeScript("window.scrollBy(0,300)");
			   Thread.sleep(2000);
			   
			   //click on add to cart
			   driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
			   Thread.sleep(2000);
			   String S1=driver.findElement(By.xpath("//*[@id=\"product-product\"]/div[1]")).getText();
			   System.out.println(S1);
			 
		}
		
		@AfterTest
		 public void closeBrowser()
		 {
			 driver.close();
		 }
	}