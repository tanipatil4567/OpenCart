package com.psl.OpenCart.Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ExcelUtils.ExcelUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrationPageTest {
	
	WebDriver driver=null;
	public static String browserName = "Chrome";
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
//	String projectPath = System.getProperty("user.dir");
	ExcelUtils exe = new ExcelUtils("RegistrationData.xlsx","sheet1");
	
	static ExtentTest test;
	static ExtentReports report;
	
	 @BeforeClass
	 public static void startTest()
	 {
	 report = new ExtentReports(System.getProperty("user.dir")+"\\final_ExtentReportResults.html");
	 test = report.startTest("Opencart_Modules");
	 }
	
	@Test(priority = 1)
	public void testBrowser() {
		
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "D://chromedriver_win32//chromedriver.exe");
		if (browserName.contains("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();}
		else if (browserName.contains("EDGE")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		
//		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
//		driver=new ChromeDriver();
//		driver.manage().window().maximize();
////		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
//		//Open Website
		driver.get("http://localhost/Opencart");
		test.log(LogStatus.PASS, "Browser Opened successfully");
//		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
//		test.log(LogStatus.PASS, "Browser opended Successfull");
	}

	@Test(priority = 2)
	public void testRegisterPager() throws IOException
	{
		driver.findElement(By.xpath("//div[@id='top-links']/ul/li[2]/a")).click();
		driver.findElement(By.linkText("Register")).click();
		test.log(LogStatus.PASS, "Page opened");

	}

	@Test(priority = 3)
	public void testFirstname() throws Exception
	{
		//String fname = firstnameData();
//		workbook = new XSSFWorkbook("C:\\Users\\tejal_borase\\eclipse-workspace\\eclipse2\\KarateProject\\Files\\ExcelData.xlsx");
//		sheet = workbook.getSheet("sheet1");
		
		String s1=exe.readFirstname();
		if(s1.matches("[A-Z][a-z]*"))
		{
			driver.findElement(By.id("input-firstname")).sendKeys(s1);
			test.log(LogStatus.PASS, "First name added");
		}
		//String s1 = sheet.getRow(1).getCell(0).getStringCellValue();
		
	}

	@Test(priority = 4)
	public void testLastname() throws Exception
	{
//		workbook = new XSSFWorkbook("C:\\Users\\tejal_borase\\eclipse-workspace\\eclipse2\\KarateProject\\Files\\ExcelData.xlsx");
//		sheet = workbook.getSheet("sheet1");
		String s1=exe.readLastname();
	//	String s1 = sheet.getRow(1).getCell(1).getStringCellValue();
		driver.findElement(By.id("input-lastname")).sendKeys(s1);
		test.log(LogStatus.PASS, "Last name added");

	}
	
	@Test(priority = 5)
	public void testEmail()throws Exception
	{
		String s1=exe.readEmail();
		driver.findElement(By.id("input-email")).sendKeys(s1);
		test.log(LogStatus.PASS, "email added");
	}
	
	@Test(priority = 6)
	public void testTelephone() throws Exception
	{
	String s1=exe.readTelephone();
	driver.findElement(By.id("input-telephone")).sendKeys(s1);
	test.log(LogStatus.PASS, "Telephone  added");
	}


	
	@Test(priority = 7)
	public void testPassword() throws Exception
	{
		String s1=exe.readPass();
		driver.findElement(By.id("input-password")).sendKeys(s1);
		test.log(LogStatus.PASS, "Password added");
	}
	
	@Test(priority = 8)
	public void testConfirmPassword() throws Exception
	{
		String s1=exe.readConfirmPass();
		driver.findElement(By.id("input-confirm")).sendKeys(s1);
		test.log(LogStatus.PASS, "Confirm Pass given added");
	}
	
	@Test(priority = 9)
	public void selectNewsletterTest()
	{
		driver.findElement(By.xpath("(//input[@name='newsletter'])[2]")).click();
		test.log(LogStatus.PASS, "Newslettle opted");
	}

	@Test(priority = 10)
	public void agreeTest()
	{
		driver.findElement(By.name("agree")).click();
		test.log(LogStatus.PASS, "Agreement done");
	}
	
	@Test(priority = 11)
	public void continueTest()
	{
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		test.log(LogStatus.PASS, "Clicked on continue");
		
	}
	
	@Test(priority = 12)
	public void createdTest()
	{
		String expected_title = "Your Account Has Been Created!";
		String actual_title = driver.getTitle();
		Assert.assertEquals(expected_title, actual_title);
		test.log(LogStatus.PASS, "New User added");
	}
	
	@AfterClass
	public static void endTest()
	{
	report.endTest(test);
	report.flush();
	}

	@Test(priority = 12)
	public void closeDriver()
	{
		driver.close();
		test.log(LogStatus.PASS, "Browser Closed successfully");
	}

	



}
