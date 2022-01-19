/**
 * 
 */
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.psl.OpenCart.pages.ShoppingCartPF;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author segu_revathi
 *
 */
public class ShoppingCartPFTest {

	WebDriver driver;
	private ShoppingCartPF scPage;
	@Parameters({"browser"})
	@BeforeTest
	
	public void setup(String browser)
	{
		
			try
			{
				//Check if parameter passed from TestNG is 'firefox'
				if(browser.equalsIgnoreCase("firefox"))
				{
				//create firefox instance
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				}
				//Check if parameter passed as 'chrome'
				else if(browser.equalsIgnoreCase("chrome"))
				{
				//set path to chromedriver.exe
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				}
				else if(browser.equalsIgnoreCase("Edge"))
				{
				//set path to Edge.exe
				//EdgeOptions options=new EdgeOptions();
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				//driver = new EdgeDriver();
				}
				else{
				//If no browser is passed throw exception
				throw new Exception("Incorrect Browser");
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		driver.manage().window().maximize();
		driver.get("http://localhost/Opencart");
		try {
			File src =new File("C:\\Users\\segu_revathi\\Desktop\\MiniProject\\LoginData.xlsx");

			FileInputStream fileinput=new FileInputStream(src);
			Workbook wb=new XSSFWorkbook(fileinput);

			XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
			String s1=sheet1.getRow(0).getCell(0).getStringCellValue();
			String s2=sheet1.getRow(0).getCell(1).getStringCellValue();
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
			catch(IOException e){
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		scPage=new ShoppingCartPF(driver);
	}
	
	@Test
	public void validateShoppingCartPFUpdateFunc()
	{
		Assert.assertTrue(scPage.validateUpdatefunc(driver));
	}
	
	@Test
	public void validateShoppingCartRemoveFunc()
	{
		Assert.assertTrue(scPage.validateRemoveFunc(driver));
	}
	
	@AfterTest
	public void closeBrowsers()
	{
		driver.close();
	}
}
