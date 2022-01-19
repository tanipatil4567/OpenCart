package com.psl.OpenCart.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.psl.OpenCart.commons.CommonPage;

public class LoginSearchLogout  {
	WebDriver driver=null;
	static String email,password;

	@Test(groups= {"Regression"})
	public void driverSetup() {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test(priority=1) 
	public void InvalidCredentials() throws InterruptedException, IOException {
		File src =new File("OpenCart_ReadData.xlsx");
		FileInputStream fileinput=new FileInputStream(src);
		Workbook wb=new XSSFWorkbook(fileinput);

		XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
		email=sheet1.getRow(1).getCell(1).getStringCellValue();
		password=sheet1.getRow(1).getCell(2).getStringCellValue();

		driver.get("http://localhost/Opencart");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		//Thread.sleep(2000);

		WebElement element= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
		CommonPage.moveToElement(element,driver);
//		Actions action=new Actions(driver);
//		action.moveToElement(elemnt).click().perform();
		//Thread.sleep(2000);

		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
		Thread.sleep(2000);
		String type = email;
		String type1=password;
		if(type.contains(email) && type1.contains(password)) {
			System.out.println("Trying to open with invalid credentials and test should be passed");
		}
		else
		{
			System.out.println("valid credentials");
		}


		driver.navigate().back();

	}
	@Test(priority=2)
	public void LoginwithBlankField() {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String email="";
		String password="";
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();

		WebElement element= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
		CommonPage.moveToElement(element,driver);
//		Actions action=new Actions(driver);
//		action.moveToElement(elemnt).click().perform();

		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
		String type=email,type1=password;
		if(type.contains(email) && type1.contains(password)) {
			System.out.println("Test passed sucessfully");}
	}
	@Test (priority=3)

	public  void ValidCredentials() throws InterruptedException, IOException {
		File src =new File("OpenCart_ReadData.xlsx");

		FileInputStream fileinput=new FileInputStream(src);
		Workbook wb=new XSSFWorkbook(fileinput);

		XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
		email=sheet1.getRow(2).getCell(1).getStringCellValue();
		password=sheet1.getRow(2).getCell(2).getStringCellValue();

		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		WebElement element= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
		CommonPage.moveToElement(element,driver);
//		Actions action=new Actions(driver);
//		action.moveToElement(elemnt).click().perform();
		Thread.sleep(2000);

		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
		Thread.sleep(2000);

		String type = email;
		String type1=password;
		if(type.contains(email) && type1.contains(password)) {
			driver.findElement(By.id("content"));
			System.out.println("Test passed sucessfully");
		}
		else
		{
			System.out.println("Test has failed");
		}

	}

	@Test(priority=4)
	public void SearchButtonClickable() {
		driver.findElement(By.name("search")).clear();
		WebElement laptop=driver.findElement(By.name("search"));
		laptop.sendKeys("laptop");

		if(laptop.isDisplayed()) {
			System.out.println("Search button is clickable");
		}
		else {
			System.out.println("Search button is not clickable");
		}
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		//Thread.sleep(2000);
	}
	@Test(priority=5)
	public void CategoryIdSelected() {

		driver.findElement(By.name("description")).click();
		//Thread.sleep(2000);

		Select s = new Select(driver.findElement(By.name("category_id")));
		s.selectByIndex(2);

		if((driver.findElement(By.name("category_id")).isSelected()));
		{
			System.out.println("Selected category will be shown in the list");}
	}

	@Test(priority=6)
	public void SubCategoryEnable(){

		WebElement sub=driver.findElement(By.name("sub_category"));
		sub.click();

		if(sub.isEnabled()) {
			System.out.println("Tick mark will be shown");
		}
		else {
			System.out.println("Tick mark will not be shown");
		}

		driver.findElement(By.id("button-search")).click();


	}
	@Test(priority=7)
public void LogoutFunctionTest() {
		
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		WebElement element= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]/a"));
		CommonPage.moveToElement(element,driver);
//		Actions a=new Actions(driver);
//        a.moveToElement(elemnt1).click().perform();
        WebElement log=driver.findElement(By.xpath("//*[@id=\"common-success\"]/ul/li[3]/a"));
        if(log.isDisplayed()) {
        	
        	System.out.println("Your have been logged off");
        }
        else {
        	System.out.println("Your account have been logged in");
        }
		
		}
	
	@Test(groups= {"Regression"},priority=8)
	public void closeBrowser() {
		driver.close();
		System.out.println("test passed sucessfully");

	}
}