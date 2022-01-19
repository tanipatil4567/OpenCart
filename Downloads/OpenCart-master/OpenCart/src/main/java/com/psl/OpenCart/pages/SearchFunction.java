package com.psl.OpenCart.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.psl.OpenCart.commons.CommonPage;

public class SearchFunction {
	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "D://chromedriver_win32//chromedriver.exe");

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://localhost/opencartapp/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		//search functionality of opencart
		driver.findElement(By.name("search")).clear();
		WebElement laptop=driver.findElement(By.name("search"));
		laptop.sendKeys("laptop");

		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		driver.findElement(By.name("description")).click();
		int num=3;
		WebElement sort=driver.findElement(By.name("category_id"));

		//		Select s = new Select(driver.findElement(By.name("category_id")));
//		s.selectByIndex(2);
		CommonPage.selectByIndex(sort, num);
		driver.findElement(By.name("sub_category")).click();
		//explicit wait
		//WebDriverWait wait=new WebDriverWait(driver,20);
		//WebElement search=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-search")));
		//search.click();
		driver.findElement(By.id("button-search")).click();
		Thread.sleep(2000);
		driver.close();


	}}



