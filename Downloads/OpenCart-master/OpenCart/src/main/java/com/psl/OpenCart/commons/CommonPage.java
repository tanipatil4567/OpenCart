package com.psl.OpenCart.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CommonPage {
	
	static Actions action;
	public static void moveToElement(WebElement element,WebDriver driver)
	{
		action =new Actions(driver);
		action.moveToElement(element).click().perform();
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
	
	public static void selectByIndex(WebElement sort, int num)
	{
		Select s=new Select(sort);
		s.selectByIndex(num);
	}
}
