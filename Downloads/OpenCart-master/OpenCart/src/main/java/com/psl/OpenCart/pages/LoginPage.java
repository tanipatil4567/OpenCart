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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.psl.OpenCart.commons.CommonPage;

public class LoginPage {
	public static void main(String[] args) throws InterruptedException, IOException{

		File src =new File("OpenCart_ReadData.xlsx");

		FileInputStream fileinput=new FileInputStream(src);
		Workbook wb=new XSSFWorkbook(fileinput);

		XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
		String email=sheet1.getRow(2).getCell(1).getStringCellValue();
		String password=sheet1.getRow(2).getCell(2).getStringCellValue();
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/opencartapp/");
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver,20);
          //login with valid username and password
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		//Thread.sleep(2000);
		WebElement element= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
//		Actions action=new Actions(driver);
//		action.moveToElement(element).click().perform();
		CommonPage.moveToElement(element,driver);
		Thread.sleep(2000);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		//WebElement submit=driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"));
		WebElement submit=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")));
		 submit.click();
		 driver.navigate().back();
		 wb.close();
         driver.close();
	}
}



