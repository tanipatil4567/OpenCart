package com.psl.OpenCart.pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.psl.OpenCart.commons.*;


public class RegistrationPage {
	public static void main(String[] args) throws Exception {
		//static WebDriver driver;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
//		String projectPath = System.getProperty("user.dir");
		ExcelUtils exe = new ExcelUtils("RegistrationData.xlsx","sheet1");
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		String fname=exe.readFirstname();
		String lname=exe.readLastname();
		String email=exe.readEmail();
		String tele=exe.readTelephone();
		String password=exe.readPass();
		String cnfrmpass= exe.readConfirmPass();
		
		//Open Website
		driver.get("http://localhost/Opencart");
		
		//Open my account option
		//div[@id='top-links']/ul/li[2]/a
		
		driver.findElement(By.xpath("//div[@id='top-links']/ul/li[2]/a")).click();
		
		//Open register module
		
		driver.findElement(By.linkText("Register")).click();
		
		//Fill the data
		driver.findElement(By.id("input-firstname")).sendKeys(fname);
		
		
		driver.findElement(By.id("input-lastname")).sendKeys(lname);
		
		driver.findElement(By.id("input-email")).sendKeys(email);
		
		
		driver.findElement(By.id("input-telephone")).sendKeys(tele);
		
		driver.findElement(By.id("input-password")).sendKeys(password);
		
		
		driver.findElement(By.id("input-confirm")).sendKeys(cnfrmpass);
		
		
		driver.findElement(By.xpath("(//input[@name='newsletter'])[2]")).click();
		
		
		driver.findElement(By.name("agree")).click();
		
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		String expected_title = "Your Account Has Been Created!";
		String actual_title = driver.getTitle();
		
		if(expected_title.equals(actual_title))
		{
			System.out.println("Registration Successful");
		}
		else
		{
			System.out.println("Email already exist");
		}
	}
	

}
