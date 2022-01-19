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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.psl.OpenCart.commons.LoggersExample;
import com.psl.OpenCart.pages.ContactUs;
import com.psl.OpenCart.pages.Currency;
import com.psl.OpenCart.pages.CurrencyFirst;
import com.psl.OpenCart.pages.ShoppingCartPF;

/**
 * @author segu_revathi
 *
 */
public class FinalTest {

	 WebDriver driver;
	 ShoppingCartPF scPage;
	 Currency cur;
	 ContactUs cuPage;
	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
//		ChromeOptions chromeOptions = new ChromeOptions();
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get("http://localhost/Opencart");
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
		scPage=new ShoppingCartPF(driver);
		cur=new Currency(driver);
	}

	@Test
	public void validateEuroCurrencyfunc()
	{
		Assert.assertTrue(cur.validateEuroCurrency(driver));
		LoggersExample.logger.info("Tested Euro currency functionality.");
	}
	
	@Test
	public void validatePoundCurrencyfunc()
	{
		Assert.assertTrue(cur.validatePoundCurrency(driver));
		LoggersExample.logger.info("Tested Pound currency functionality.");
	}
	
	@Test
	public void validateUSDollarCurrencyfunc()
	{
		Assert.assertTrue(cur.validateUSDollarCurrency(driver));
		LoggersExample.logger.info("Tested USDollar functionality.");
	}
	
	@Test(priority=1)
	public void validateEmptyCart()
	{
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a")).click();
		System.out.println("1");
		String observedText= driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText();
		if(observedText.equals("Your shopping cart is empty!"))
		{
			LoggersExample.logger.info("Shopping cart is empty! Added product");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,2000)");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]")).click();
		}
	}
	
	@Test(priority=2)
	public void validateShoppingCartPFUpdateFunc()
	{
		Assert.assertTrue(scPage.validateUpdatefunc(driver));
		LoggersExample.logger.info("Tested Update items in cart functionality.");
		
	}
	
	@Test(priority=3)
	public void validateShoppingCartFinalTotalPriceCheck()
	{
		Assert.assertTrue(scPage.validateFinalTotal(driver));
		LoggersExample.logger.info("Testedwhether final price is correct.");
	}
	
	@Test(priority=4)
	public void validateAddCouponFunc() throws InterruptedException
	{
		Assert.assertTrue(scPage.validateAddCoupon(driver));
		LoggersExample.logger.info("Tested Add Coupon functionality.");
	}
	
	@Test(priority=5)
	public void validateShoppingCartRemoveFunc()
	{
		Assert.assertTrue(scPage.validateRemoveFunc(driver));
		LoggersExample.logger.info("Tested Remove item from cart functionality.");
	}
	
	
	@AfterTest
	public void closeBrowsers()
	{
		driver.close();
		LoggersExample.logger.info("Browser closed successfully!!!");
	}
}
