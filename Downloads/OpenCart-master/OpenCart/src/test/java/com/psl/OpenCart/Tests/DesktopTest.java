package com.psl.OpenCart.Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.psl.OpenCart.commons.CommonPage;
import com.psl.OpenCart.commons.LoggersExample;

public class DesktopTest {
WebDriver driver=null;
	
	@Test(groups= {"Regression"})
	public void driverSetup() {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		
	}
	
	@Test(priority=1)
	public void openBrowser() {
		driver.get("http://localhost/Opencart");
	     //logger.info("Chrome opened successfully!!!");
		
	}
	@Test(priority=2)
	public void openDesktopModule(){
        WebElement desktop=driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a"));
        		desktop.click();
        if(desktop.isEnabled()) {
        	LoggersExample.logger.info("Desktop module is enable");
        }
		driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/div/a")).click();
		
	}
	@Test(priority=3)
	public void sortProductByPrice(){
		Select s = new Select(driver.findElement(By.id("input-sort")));
		s.selectByIndex(3);
	
	}
	@Test(priority=4)
	public void ShowingByList()  {
		driver.findElement(By.id("list-view")).click();
		}
	
	@Test(priority=5)
	public void selectProduct(){
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/div[1]/div/div[2]/div[1]/h4/a")).click();
		
		
	}
	@Test(priority=6)
	public void addToWishlist() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/button[1]")).click();
		
	}
	
	@Test(priority=7)
	public void addToCart() {
		Select s1 = new Select(driver.findElement(By.id("input-option226")));
		s1.selectByIndex(1);
		driver.findElement(By.id("button-cart")).click();
		
	}
	@AfterTest
	public void closeBrowser() {
		driver.close();
		 LoggersExample.logger.info("Browser closed successfully!!!");
	}


}
