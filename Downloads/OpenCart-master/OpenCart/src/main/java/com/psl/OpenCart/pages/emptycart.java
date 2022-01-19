/**
 * 
 */
package com.psl.OpenCart.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.psl.OpenCart.commons.LoggersExample;

/**
 * @author segu_revathi
 *
 */
public class emptycart {
	private static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/opencartpro/");
		
		LoggersExample.logger.info("Browser opened successfully!!!");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		try {
			File src =new File("C:\\Users\\segu_revathi\\Desktop\\MiniProject\\LoginData.xlsx");

			FileInputStream fileinput=new FileInputStream(src);
			Workbook wb=new XSSFWorkbook(fileinput);

			XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
			String s1=sheet1.getRow(0).getCell(0).getStringCellValue();
			String s2=sheet1.getRow(0).getCell(1).getStringCellValue();
			driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();

			WebElement elemnt= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
			Actions a=new Actions(driver);
			a.moveToElement(elemnt).click().perform();

			driver.findElement(By.name("email")).sendKeys(s1);
			driver.findElement(By.name("password")).sendKeys(s2);
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a")).click();
		System.out.println("1");
		String observedText= driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText();
		System.out.println(observedText);
		Thread.sleep(2000);
		if(observedText.equals("Your shopping cart is empty!"))
		{
			LoggersExample.logger.info("Shopping cart is empty! Added product");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,2000)");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]")).click();
			
		}
		
		driver.close();
	}

}
