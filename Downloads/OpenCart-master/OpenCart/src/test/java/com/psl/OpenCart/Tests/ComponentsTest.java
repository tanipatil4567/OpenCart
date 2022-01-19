package com.psl.OpenCart.Tests;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ComponentsTest 
{
   WebDriver driver;
   
   @BeforeTest  // annotation
   public void setUp()
   {
	   System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
	     driver=new ChromeDriver();
	   //  driver.get("http://localhost/opencartpro1/index.php?route=common/home");
	     driver.get("http://localhost/Opencart");
	     driver.manage().window().maximize();
	     driver.manage().deleteAllCookies();
	     System.out.println("Chrome opened Sucessfully");
   }
   @Test(priority = 1)
   public void verifyComponentsModuleInHomePage()
   {
	// Checking whether the components module is present in the Home page
	   boolean flag= driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/a")).isDisplayed();
	   Assert.assertTrue(flag);
	   System.out.println("components module is present in the Home page");
   }
   
   @Test(priority = 2)
   public void verifyComponentsIsClickable() throws InterruptedException
   {   
	   // Check whether the module is clickable or not
	   driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/a")).click();
	   System.out.println("Clicked on the components module");
	   Thread.sleep(2000);
	   //clicking on Show all components in drop-down list
	   driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/div/a")).click();
   }
   
@Test(priority = 3)
   public void addingToCart() throws InterruptedException, IOException
   {
	   verifyComponentsIsClickable();
	   //Clicking on Monitors
	   driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/ul/li[2]/a")).click();
	   Thread.sleep(2000);
	   
	   //Scrolling
	   JavascriptExecutor js=(JavascriptExecutor)driver;
	   js.executeScript("window.scrollBy(0,300)");
	   Thread.sleep(2000);
	   
	   //clicking on the list option
	   driver.findElement(By.xpath("//*[@id=\"list-view\"]/i")).click();
	   Thread.sleep(2000);
	   // Adding Apple Cinema 30" to Cart
	   driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[2]/button[1]")).click();
	   Thread.sleep(2000);
	   
	   //Scrolling
	   js.executeScript("window.scrollBy(0,700)");
	   Thread.sleep(2000);
	   
	   //Selecting the radio button
	   driver.findElement(By.xpath("//*[@id=\"input-option218\"]/div[2]/label/input")).click();
	   Thread.sleep(2000);
	   
	   //Selecting the check-box
	   driver.findElement(By.xpath("//*[@id=\"input-option223\"]/div[2]/label")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id=\"input-option208\"]")).sendKeys("Good");
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id=\"input-option217\"]")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id=\"input-option217\"]/option[5]")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id=\"input-option209\"]")).sendKeys("Hello Everyone");
	   Thread.sleep(2000);
	   
	   js.executeScript("window.scrollBy(0,500)");
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id=\"button-upload222\"]")).click();
	   Thread.sleep(2000);
	   
	   // Using AutoIT to Upload the file
	   Runtime.getRuntime().exec("C:\\Users\\tejal_borase\\Desktop\\Mini Project Phase 2\\Files\\FileUpload.exe");
	   Thread.sleep(5000);
	   
       //Accepting the Alert 
	   //driver.switchTo().alert().accept();
       Alert alertOK = driver.switchTo().alert();
       alertOK.accept();
       Thread.sleep(2000);
	   
	   driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
	   Thread.sleep(1000);
	   js.executeScript("window.scrollBy(0,-1500)");
	   Thread.sleep(3000);
   }
   
	 @AfterTest
	 public void closeBrowser()
	 {
		 driver.close();
	 }
   }
