/**
 * 
 */
package com.psl.OpenCart.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.psl.OpenCart.commons.LoggersExample;
/**
 * @author segu_revathi
 *
 */
public class SeleniumImplementation {
	double sum;
	String li;
	NumberFormat formatter = new DecimalFormat("#0.00");   
	Actions action;
	public void moveToElement(WebElement element,WebDriver driver)
	{
		action =new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	public void click(WebElement element)
	{
		element.click();
	}
	
	public void clear(WebElement element)
	{
		element.clear();
	}
	
	public void setText(String text, WebElement element)
	{
		element.sendKeys(text);
	}
	
	public void selectByVisibleText(WebElement selectOption, String text)
	{
		Select s=new Select(selectOption);
		s.selectByVisibleText(text);
	}
	
	public void scroll(String string,WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String cmd = "window.scrollBy(0,"+string+")";
		js.executeScript(cmd);
	}
	
	public boolean validateTExt(WebElement element, WebDriver driver, String expectedText)
	{
		String observedText=element.getText();
		//System.out.println(observedText);
		if(observedText.equals(expectedText))
		{
			return true;
		}
		return false;
	}
	
	public boolean finalPriceCheck(WebDriver driver,List<WebElement> itemTotals, WebElement finalTotal)
	{
		String x;
		sum=0;
		List<WebElement> values=itemTotals;
		for (int i = 1; i < values.size(); i++) {
			x=values.get(i).getText();
			li = "";
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
			//System.out.println(li);
			sum+=Double.parseDouble(li);
		}
		String finalPrice=finalTotal.getText();
		String li = "";
		for(int j=0; j<finalPrice.length(); j++)
		{
	         if(Character.isDigit(finalPrice.charAt(j))) {
	            li+=finalPrice.charAt(j);
	         }
	         else if(finalPrice.charAt(j)=='.')
	         {
	        	 li+=finalPrice.charAt(j);
	         }
		}
		if(Double.parseDouble(li)==sum)
		{
			return true;
		}
		
		return false;
	}
	
	public double findFinalTotalPrice(WebDriver driver)
	{
		String finalPrice=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/table/tbody/tr[3]/td[2]")).getText();
		String li = "";
		for(int j=0; j<finalPrice.length(); j++)
		{
	         if(Character.isDigit(finalPrice.charAt(j))) {
	            li+=finalPrice.charAt(j);
	         }
	         else if(finalPrice.charAt(j)=='.')
	         {
	        	 li+=finalPrice.charAt(j);
	         }
		}
		return Double.parseDouble(li);
	}
	public boolean addCouponCheck(WebDriver driver ) 
	{
		Double tot=findFinalTotalPrice(driver);
		double couponPrice=Double.parseDouble(formatter.format((sum-((sum)/10))));
		if(couponPrice==tot)
		{
			LoggersExample.logger.info("Coupon applied and deducted 10%");
			//System.out.println("Coupon applied and deducted 10%");
			return true;
		}
		return false;
	}

	public boolean checkCurrency(WebElement symbol, WebDriver driver, String string) {
		if((symbol.getText()).equals(string))
		{
			System.out.println("Converted to"+string+"currency successfully");
			return true;
		}
		return false;
	}
	
	
	//ramya
	
	public void validateTExt1(WebElement element, WebDriver driver, String expectedText) {
		String s1 = "Your enquiry has been successfully sent to the store owner!";
		String m1 = driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText();
		System.out.println(m1);
		if (s1.equalsIgnoreCase(m1)) {
			System.out.println(" successfully submitted");
		} else {
			System.out.println("E-Mail Address does not appear to be valid!");
		}
	}

	public void InvalidateTExt(WebElement element, WebDriver driver, String expectedText) {
		String s1 = "E-Mail Address does not appear to be valid!";
		String m1 = driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset/div[2]/div/div")).getText();
		System.out.println(m1);
		if (s1.equalsIgnoreCase(m1)) {
			System.out.println(" enter correct email");
		} else {
			System.out.println("E-Mail Address does not appear to be valid!");
		}
	}

	public void validateTExtInEditAccountInfo(WebElement element, WebDriver driver, String expectedText) {
		String s1 = " Success: Your account has been successfully updated.";
		String m1 = driver.findElement(By.xpath("//*[@id=\"account-account\"]/div[1]")).getText();
		System.out.println(m1);
		if (s1.equalsIgnoreCase(m1)) {
			System.out.println("Personal info is updated succesfully");
		} else {
			System.out.println("E-Mail Address does not appear to be valid!");
		}
	}

	public void InvalidateTExtInEditAccountInfo(WebElement element, WebDriver driver, String expectedText) {
		String s1 = " E-Mail Address does not appear to be valid!.";
		String m1 = driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset/div[3]/div/div")).getText();
		System.out.println(m1);
		if (s1.equalsIgnoreCase(m1)) {
			System.out.println("Personal info is not updated. enter corect email");
		} else {
			System.out.println("E-Mail Address does not appear to be valid!");
		}
	}
}
