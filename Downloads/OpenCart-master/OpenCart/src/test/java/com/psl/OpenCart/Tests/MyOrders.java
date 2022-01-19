package com.psl.OpenCart.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MyOrders {
	WebDriver driver=null;
	@Test(priority = 1)
	public void openBrowserTest(){
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/Opencart");
	}
	@Test(priority =2)
	public void loginToPage()throws Throwable{
		 File input= new File("login.xlsx");
			
			FileInputStream fis = new FileInputStream(input);
			Workbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet s1 = (XSSFSheet) wb.getSheetAt(0);
			String email=s1.getRow(1).getCell(0).getStringCellValue();
			String pwd=s1.getRow(1).getCell(1).getStringCellValue();
		
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")).click();
		
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(pwd);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input[1]")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		wb.close();
	}
	@Test(priority =3)
	public void viewMyOrders() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li[1]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	@Test(priority =4)
	public void viewDownloads() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	@Test(priority =5)
	public void rewardPoints() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li[3]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	@Test(priority =6)
	public void returnRequest() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath(" //*[@id=\"content\"]/div/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	@Test(priority =7)
	public void transcationDetails() {
		driver.findElement(By.xpath(" //*[@id=\"content\"]/ul[2]/li[5]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath(" //*[@id=\"content\"]/div[3]/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	@Test(priority =8)
	public void recurringPoints() {
		driver.findElement(By.xpath(" //*[@id=\"content\"]/ul[2]/li[6]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	@Test(priority =9)
	public void closeBrowser() {
		driver.close();
		System.out.println("Test cases Passed Successfully");
	}
}
