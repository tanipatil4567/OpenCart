package com.psl.OpenCart.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.psl.OpenCart.commons.LoggersExample;

public class ShoppingCart {
	static ShoppingCart sc=new ShoppingCart();
		static WebDriver driver;
		public static double sum=0;

		/*
		 * public static void main(String[] args) throws InterruptedException,
		 * IOException { Login login=new Login(); login.login_page(); ShoppingCart
		 * SC=new ShoppingCart(); SC.shoppingCart(); }
		 */
		
		public static void main(String[] args) throws InterruptedException,IOException
		{
			File src =new File("C:\\Users\\segu_revathi\\Desktop\\MiniProject\\LoginData.xlsx");

			FileInputStream fileinput=new FileInputStream(src);
			Workbook wb=new XSSFWorkbook(fileinput);

			XSSFSheet sheet1=(XSSFSheet) wb.getSheetAt(0);
			String s1=sheet1.getRow(0).getCell(0).getStringCellValue();
			String s2=sheet1.getRow(0).getCell(1).getStringCellValue();
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
			
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get("http://localhost/opencartpro/");
			driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();

			WebElement elemnt= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
			Actions a=new Actions(driver);
			a.moveToElement(elemnt).click().perform();

			driver.findElement(By.name("email")).sendKeys(s1);
			driver.findElement(By.name("password")).sendKeys(s2);
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
			
			
			//Update count function
			driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a/span")).click();
			WebElement countOfItems=driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/input"));
			Actions a1=new Actions(driver);
			a1.moveToElement(countOfItems).click().perform();
			countOfItems.clear();
			//LoggersExample.logger.info("Cleared existing count");
			countOfItems.sendKeys("10");
			//LoggersExample.logger.info("Updated value of count 10 is given");
			WebElement update= driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/span/button[1]"));
			update.click();
			//LoggersExample.logger.info("Updated successfully");
			WebElement successmsg=driver.findElement(By.cssSelector(".alert.alert-success.alert-dismissible"));
			System.out.println(successmsg.getText());
			
			String subtotal = driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[6]")).getText();
			System.out.println(subtotal);
			//LoggersExample.logger.info("Print subtotoal successfully");
			
			
			//checking total price = sum 
			sum=0;
			double d;
			String x;
			//click on shopping cart
			driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a/span")).click();
			List<WebElement> values=driver.findElements(By.cssSelector(".table.table-bordered td:nth-child(6)"));
			for (int i = 1; i < values.size(); i++) {
				x=values.get(i).getText();
				String li = "";
				for(int j=0; j<x.length(); j++)
				{
			         if(Character.isDigit(x.charAt(j))) {
			            li+=x.charAt(j);
			         }
			         else if(x.charAt(j)=='.')
			         {
			        	 li+=x.charAt(j);
			         }
				}
				System.out.println(li);
				sum+=Double.parseDouble(li);
			}
			System.out.println("Total price we got: "+sum);
			
			String finalTotal=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/table/tbody/tr[2]/td[2]")).getText();
			String li = "";
			for(int j=0; j<finalTotal.length(); j++)
			{
		         if(Character.isDigit(finalTotal.charAt(j))) {
		            li+=finalTotal.charAt(j);
		         }
		         else if(finalTotal.charAt(j)=='.')
		         {
		        	 li+=finalTotal.charAt(j);
		         }
			}
			if(Double.parseDouble(li)==sum)
			{
				System.out.println("Final Total Price is correct");
			}
			else
			{
				System.out.println("Final Total Price is not correct");
			}
			
			
			//add coupon function
			WebElement couponDropDown= driver.findElement(By.xpath("//*[@id=\"accordion\"]/div[1]/div[1]/h4/a"));
			couponDropDown.click();
			WebElement couponInput=driver.findElement(By.xpath("//*[@id=\"input-coupon\"]"));
			a.moveToElement(couponInput).click().perform();
			couponInput.sendKeys("2222");
			driver.findElement(By.id("button-coupon")).click();
//			String couponmsg=driver.findElement(By.cssSelector(".alert.alert-success.alert-dismissible")).getText();
//			if(couponmsg.equals("Success: Your coupon discount has been applied!"))
//			{
//				System.out.println("Coupon applied successfully");
//			}
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,2000)");
			
			
			finalTotal=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/table/tbody/tr[3]/td[2]")).getText();
			li = "";
			for(int j=0; j<finalTotal.length(); j++)
			{
		         if(Character.isDigit(finalTotal.charAt(j))) {
		            li+=finalTotal.charAt(j);
		         }
		         else if(finalTotal.charAt(j)=='.')
		         {
		        	 li+=finalTotal.charAt(j);
		         }
			}
			if((sum-((10*sum)/100))==Double.parseDouble(li))
			{
				System.out.println("Coupon applied and deducted 10%");
			}
			System.out.println("New total price is: "+Double.parseDouble(li));

			driver.close();
			
		}
		
		
		//methods
		public double totalFinalPrice()
		{
			String finalTotal=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/table/tbody/tr[2]/td[2]")).getText();
			String li = "";
			for(int j=0; j<finalTotal.length(); j++)
			{
		         if(Character.isDigit(finalTotal.charAt(j))) {
		            li+=finalTotal.charAt(j);
		         }
		         else if(finalTotal.charAt(j)=='.')
		         {
		        	 li+=finalTotal.charAt(j);
		         }
			}
			return(Double.parseDouble(li));
		}
}
