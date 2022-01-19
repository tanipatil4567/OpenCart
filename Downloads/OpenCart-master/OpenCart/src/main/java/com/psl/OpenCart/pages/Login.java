package com.psl.OpenCart.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Login {

	public void login_page() throws IOException, InterruptedException {
		
		File src =new File("C:\\Users\\segu_revathi\\Desktop\\MiniProject\\LoginData.xlsx");

		FileInputStream fileinput=new FileInputStream(src);
		Workbook wb=new XSSFWorkbook(fileinput);

		XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
		String s1=sheet1.getRow(0).getCell(0).getStringCellValue();
		String s2=sheet1.getRow(0).getCell(1).getStringCellValue();
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/opencartpro/");
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		Thread.sleep(2000);

		WebElement elemnt= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
		Actions a=new Actions(driver);
		a.moveToElement(elemnt).click().perform();
		Thread.sleep(2000);

		driver.findElement(By.name("email")).sendKeys(s1);
		driver.findElement(By.name("password")).sendKeys(s2);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		

	}
	}

