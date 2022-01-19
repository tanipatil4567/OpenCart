package com.psl.OpenCart.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class newAddress {
	
	
	
	
	
	WebDriver d;
	@BeforeTest
	public void start() {
	  System.setProperty("webdriver.chrome.driver","D:/chromedriver.exe");
	  d = new ChromeDriver(); 
	  d.get("http://localhost/Opencart");
	  try {
			File src =new File("C:\\Users\\segu_revathi\\Desktop\\MiniProject\\LoginData.xlsx");

			FileInputStream fileinput=new FileInputStream(src);
			Workbook wb=new XSSFWorkbook(fileinput);

			XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
			String s1=sheet1.getRow(0).getCell(0).getStringCellValue();
			String s2=sheet1.getRow(0).getCell(1).getStringCellValue();
			d.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();

			WebElement elemnt= d.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
			Actions a=new Actions(d);
			a.moveToElement(elemnt).click().perform();

			d.findElement(By.name("email")).sendKeys(s1);
			d.findElement(By.name("password")).sendKeys(s2);
			d.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
			}
			catch(IOException e){
				e.printStackTrace();
			}
	} 
	
	@Test(priority=1)
	public void login() throws InterruptedException {
		d.manage().window().maximize();
		//login with valid credential
		
	}
	
	@Test(priority=2)
	public void Mp3Player_menu() throws InterruptedException{

		WebElement d1 = d.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[8]/a"));
		WebElement d2 = d.findElement(By.xpath("//a[contains(text(),'Show All MP3 Players')]"));
		
		Actions a = new Actions(d);
		
		Actions ad1 = a.moveToElement(d1).click();
		ad1.perform();
		Thread.sleep(2000);
		
		Actions ad2 = a.moveToElement(d2).click();
		ad2.perform();
		Thread.sleep(2000);
	}
	
	@Test(priority=3)
	public void Mp3_sorting() throws InterruptedException {
		//Sorting 
		Select s = new Select(d.findElement(By.id("input-sort")));
		s.selectByIndex(2);
		Thread.sleep(2000);
	}
	
	@Test(priority=4)
	public void Mp3_List() throws InterruptedException {
		//Show in List
		d.findElement(By.id("list-view")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority=5)
	public void Mp3_select() throws InterruptedException{
		//Select iPod Touch
		d.findElement(By.xpath("//*[@id=\"content\"]/div[4]/div[1]/div/div[2]/div[1]/h4/a")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority=6)
	public void Mp3_wishList() throws InterruptedException{
		//Add to wish list 
		d.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/button[1]")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority=7)
	public void Mp3_addToCart() throws InterruptedException{
		//Add to cart 
		d.findElement(By.id("button-cart")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority=8)
	public void addressbook() throws InterruptedException, IOException {
		
		d.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		Thread.sleep(1000);
		d.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a")).click();
		Thread.sleep(1000);
		d.findElement(By.xpath("//*[@id=\"column-right\"]/div/a[4]")).click();
		Thread.sleep(1000);
		
		d.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/a")).click();
		Thread.sleep(1000);
	}
		
	//Add details in new address
	@Test(priority=9)
	public void newAddress_data() throws InterruptedException {
		
		d.findElement(By.id("input-firstname")).sendKeys("Neha");	
		d.findElement(By.id("input-lastname")).sendKeys("Roy");
		d.findElement(By.id("input-company")).sendKeys("PSL");
		d.findElement(By.id("input-address-1")).sendKeys("House No. 103, XYZ street");
		d.findElement(By.id("input-city")).sendKeys("Durgapur");
		d.findElement(By.id("input-postcode")).sendKeys("98765");
		
		Select country = new Select(d.findElement(By.xpath("//*[@id=\"input-country\"]")));
		country.selectByVisibleText("India");
		
		Actions a = new Actions(d);
		
		WebElement s = d.findElement(By.xpath("//select[@id='input-zone']"));
		
		Action as = a.moveToElement(s).click().keyDown(s, Keys.SHIFT).sendKeys(s, "West Bengal").build();
		as.perform();
		
		//radio button 	- 'yes'
		//d.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset/div[10]/div/label[1]/input")).click();
		//Thread.sleep(500);
		// click continue button	
		d.findElement(By.xpath("//*[@id=\"content\"]/form/div/div[2]/input")).click();
		Thread.sleep(800);	
		d.navigate().back();
	}
	
	@AfterTest
	public void closeBrowser() {
		d.close();
	}
	
}
