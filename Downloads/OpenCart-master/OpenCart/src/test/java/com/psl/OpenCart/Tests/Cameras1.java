package com.psl.OpenCart.Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Cameras1 {
	WebDriver driver=null;
	@Test(priority = 1)
	public void openBrowserTest(){
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		//Open Website 
		driver.get("http://localhost/Opencart");
	}
	@Test(priority = 2)
	public void viewcameras(){
		driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[7]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
	}

	@Test(priority = 3)
	public void openCameras() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[1]/a/img")).click();
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
		driver.navigate().back();

		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[1]/a/img")).click();
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
		driver.navigate().back();
	}
	@Test(priority =4)
	public void sort() {
		Select s = new Select(driver.findElement(By.id("input-sort")));
		s.selectByIndex(3);
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
	}
	@Test(priority =5)
	public void closeBrowser() {
		driver.close();
		System.out.println("Test cases passed successfully");
}
}
