package com.psl.OpenCart.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.psl.OpenCart.commons.CommonPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ExcelUtils.ExcelUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTestcasesFinal {
	WebDriver driver=null;
	public static String browserName = "Chrome";
	static String email,password;
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
	public void driverSetup() {
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
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
	}
	
	

	@Test(priority = 2)
	public void testRegisterPager() throws IOException
	{
		driver.get("http://localhost/Opencart");
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
	public void continueTest() throws Exception
	{
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		test.log(LogStatus.PASS, "Clicked on continue");
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 12)
	public void createdTest()
	{
		String expected_title = "Your Account Has Been Created!";
		String actual_title = driver.getTitle();
		Assert.assertEquals(expected_title, actual_title);
		test.log(LogStatus.PASS, "New User added");
	}

	
	
	
	
	
	//Login
	@Test(priority=13) 
	public void InvalidCredentials() throws InterruptedException, IOException {
		File src =new File("OpenCart_ReadData.xlsx");
		FileInputStream fileinput=new FileInputStream(src);
		Workbook wb=new XSSFWorkbook(fileinput);

		XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
		email=sheet1.getRow(1).getCell(1).getStringCellValue();
		password=sheet1.getRow(1).getCell(2).getStringCellValue();

//		driver.get("http://localhost/Opencart");
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
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
			test.log(LogStatus.FAIL, "Invalid Credential for login");
		}
		else
		{
			System.out.println("valid credentials");
		}


		driver.navigate().back();

	}
	@Test(priority=14)
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
			test.log(LogStatus.PASS, "logged in  succesfully");
			
			
		}
	}
	@Test (priority=15)

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
			test.log(LogStatus.PASS, "logged in succesfully");
			
		}
		else
		{
			test.log(LogStatus.FAIL, "logged in failed");
		}

	}
	//MyOrders

	@Test(priority =16)
	public void viewMyOrders() {
		driver.navigate().back();
		//Thread.sleep(2000);
		driver.navigate().back();
		//Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li[1]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		test.log(LogStatus.PASS, "Oders viewed");
	}
	@Test(priority =17)
	public void viewDownloads() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		test.log(LogStatus.PASS, "View Downloads");
	}
	@Test(priority =18)
	public void rewardPoints() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li[3]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		test.log(LogStatus.PASS, "Reward Points");
	}
	@Test(priority =19)
	public void returnRequest() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		test.log(LogStatus.PASS, "Return Request");
	}

	//search

	@Test(priority=20)
	public void SearchButtonClickable() {
		driver.findElement(By.name("search")).clear();
		WebElement laptop=driver.findElement(By.name("search"));
		laptop.sendKeys("laptop");

		if(laptop.isDisplayed()) {
			test.log(LogStatus.PASS, "Search button Clickable");
		}
		else {
			test.log(LogStatus.FAIL, "Search button not Clickable");
		}
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		//Thread.sleep(2000);
	}
	@Test(priority=21)
	public void CategoryIdSelected() {

		driver.findElement(By.name("description")).click();
		//Thread.sleep(2000);

		Select s = new Select(driver.findElement(By.name("category_id")));
		s.selectByIndex(2);

		if((driver.findElement(By.name("category_id")).isSelected()));
		{
			System.out.println("Selected category will be shown in the list");}
		test.log(LogStatus.PASS, "CategoryIdSelected");
		
	}

	@Test(priority=22)
	public void SubCategoryEnable(){

		WebElement sub=driver.findElement(By.name("sub_category"));
		sub.click();

		if(sub.isEnabled()) {
			System.out.println("Tick mark will be shown");
			test.log(LogStatus.PASS, "openDesktopModule");
		}
		else {
			System.out.println("Tick mark will not be shown");
		}

		driver.findElement(By.id("button-search")).click();
		Navigation manage=driver.navigate();
		manage.back();
		test.log(LogStatus.PASS, "SubCategoryEnable");
		//manage.back();
		//manage.back();



	}
	//Desktop
	@Test(priority=23)
	public void openDesktopModule() throws InterruptedException {
		WebElement desktop=driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a"));
		desktop.click();
		if(desktop.isEnabled()) {
			System.out.println("Desktop module is enable");
			test.log(LogStatus.PASS, "openDesktopModule");
		}
		driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/div/a")).click();
		//Thread.sleep(2000);

	}
	@Test(priority=24)
	public void sortProductByPrice() throws InterruptedException {
		Select s = new Select(driver.findElement(By.id("input-sort")));
		s.selectByIndex(3);
		//Thread.sleep(2000);
		test.log(LogStatus.PASS, "sort ProductBy Price");

	}
	@Test(priority=25)
	public void ShowingByList() throws InterruptedException {
		driver.findElement(By.id("list-view")).click();
		Thread.sleep(2000);
		test.log(LogStatus.PASS, "Showing by List");

	}

	@Test(priority=26)
	public void selectProduct() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/div[1]/div/div[2]/div[1]/h4/a")).click();
		//Thread.sleep(2000);
		test.log(LogStatus.PASS, "selected product");

	}
	@Test(priority=27)
	public void addToWishlist() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/button[1]")).click();
		//Thread.sleep(5000);

		test.log(LogStatus.PASS, "addToWishlist");
	}

	@Test(priority=28)
	public void addToCart() throws InterruptedException {
		Select s1 = new Select(driver.findElement(By.id("input-option226")));
		s1.selectByIndex(1);
		//Thread.sleep(2000);
		driver.findElement(By.id("button-cart")).click();
		//Thread.sleep(5000);

		//driver.close();
		driver.navigate().back();
		test.log(LogStatus.PASS, "addToCart");
		
	}

	//Wish-list

	@Test(priority=29)
	public void removeFromWishlist() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"wishlist-total\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/a")).click();
		test.log(LogStatus.PASS, "removeFromWishlist");


	}

	//mp3 product
	@Test(priority=30)
	public void Mp3FunctionButtonTest() {
		driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[8]/a")).click();

		driver.findElement(By.xpath("//a[contains(text(),'Show All MP3 Players')]")).click();

		//Sorting 
		Select s = new Select(driver.findElement(By.id("input-sort")));
		s.selectByIndex(2);
		//Show in List
		driver.findElement(By.id("list-view")).click();
		//Select iPod Touch
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/div[1]/div/div[2]/div[1]/h4/a")).click();
		//Added to wish list 
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/button[1]")).click();

		//Add to cart
		driver.findElement(By.id("button-cart")).click();
		test.log(LogStatus.PASS, "Mp3FunctionButtonTest");


	}
	
	
	////PHONE AND PDAS
	
	@Test(priority = 31)
	   public void verifyPhonesAndPDAsModuleInHomePage() throws InterruptedException
	   {
		// Checking whether the PhonesAndPDAs module is present in the Home page
		   boolean flag= driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[4]/a")).isDisplayed();
		   Assert.assertTrue(flag);
		   Thread.sleep(2000);
		   System.out.println("PhonesAndPDAs module is present in the Home page");
	   }
	
	@Test(priority = 32)
	public void verifyPhonesAndPDAsIsClickable() throws InterruptedException
	{
		 //Clicking on the PhonesAndPDAs module
		   driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[6]/a")).click();
		   Thread.sleep(2000);
	}
	
	
	@Test(priority = 33)
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

//	@Test(priority = 34)
//	public void checkout() throws InterruptedException {
//		driver.findElement(By.xpath("//*[@id=\\\"top-links\\\"]/ul/li[5]/a/span")).click();
//		Thread.sleep(1000);
//		
//		driver.findElement(By.xpath("//*[@id=\"button-payment-address\"]")).click();
//		Thread.sleep(1000);
//		
//		driver.findElement(By.id("button-shipping-address")).click();
//		Thread.sleep(1000);
//		
//		driver.findElement(By.id("button-shipping-method")).click();
//		Thread.sleep(1000);
//		
//		driver.findElement(By.name("agree")).click();
//		Thread.sleep(1000);
//		
//		driver.findElement(By.id("button-payment-method")).click();
//		Thread.sleep(1000);
//		
//		driver.findElement(By.id("button-confirm")).click();
//		Thread.sleep(2000);
//		
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, 5);
//		    wait.until(ExpectedConditions.alertIsPresent());
//		    Alert alert = driver.switchTo().alert();
//		    Assert.assertTrue(alert.getText().contains("OK"));
//		    alert.accept();
//		}
//		catch(Exception e) {
//			
//			System.out.println("Exception: "+e);
//		}
//		Thread.sleep(2000);
//		
//		driver.findElement(By.id("button-confirm")).click();
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
//		Thread.sleep(1000);
//		
//	}
	

	//Logout

	@Test(priority=35)
	public void LogoutFunctionTest() {

		driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		WebElement element= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]/a"));
		CommonPage.moveToElement(element,driver);
		
		WebElement log=driver.findElement(By.xpath("//*[@id=\"common-success\"]/ul/li[3]/a"));
		if(log.isDisplayed()) {

			System.out.println("Your have been logged off");
			test.log(LogStatus.PASS, "loggedoff succesfully");
		}
		else {
			System.out.println("Your account have been logged in");
			test.log(LogStatus.FAIL, "Your account have been logged in");
		}


	}
	
	
	////CURRENCY
	
	

	
	@AfterClass
	public static void endTest()
	{
	report.endTest(test);
	report.flush();
	}
	
//	@Test(priority = 36)
//	public void closeBrowser() {
//		driver.close();
//		test.log(LogStatus.PASS, "closeBrowser closed successfully");

	//}
}