package com.psl.OpenCart.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.psl.OpenCart.commons.CommonPage;

public class DesktopFunction {
	public static void main(String args[]) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D://chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);

		driver.get("http://localhost/opencartapp/");
		driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/div/a")).click();
		//Thread.sleep(2000);
		WebElement sort=driver.findElement(By.id("input-sort"));
		int num=3;
		CommonPage.selectByIndex( sort,  num);

		//show in list
		driver.findElement(By.id("list-view")).click();
		//Thread.sleep(2000);
		
		//select product
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/div[1]/div/div[2]/div[1]/h4/a")).click();
		Thread.sleep(3000);

		//add to wish list
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/button[1]")).click();

		//Add to cart
		
		Select s1 = new Select(driver.findElement(By.id("input-option226")));
		s1.selectByIndex(1);
		
		Thread.sleep(2000);
		driver.findElement(By.id("button-cart")).click();

		driver.close();



	}
}



