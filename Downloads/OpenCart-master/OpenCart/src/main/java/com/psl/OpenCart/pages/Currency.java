/**
 * 
 */
package com.psl.OpenCart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.psl.OpenCart.commons.SeleniumImplementation;

/**
 * @author segu_revathi
 *
 */
public class Currency {
	
	@FindBy(xpath="//*[@id=\"account-account\"]/ul/li[1]/a")
	WebElement home;
	@FindBy(xpath="//*[@id=\"form-currency\"]/div/button")
	WebElement currencyDropDown;
	@FindBy(xpath="//*[@id=\"form-currency\"]/div/ul/li[1]/button")
	WebElement currencyEuro;
	@FindBy(xpath="//*[@id=\"form-currency\"]/div/button/strong")
	WebElement symbol;
	@FindBy(xpath="//*[@id=\"form-currency\"]/div/ul/li[2]/button")
	WebElement currencyPound;
	@FindBy(xpath="//*[@id=\"form-currency\"]/div/ul/li[3]/button")
	WebElement currencyUSDollar;
	
	
	SeleniumImplementation selenium;
	Actions action;

	public Currency(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		selenium=new SeleniumImplementation();
	}
	
	public boolean validateEuroCurrency(WebDriver driver)
	{
		//selenium.click(home);
		selenium.click(currencyDropDown);
		selenium.click(currencyEuro);
		return selenium.checkCurrency(symbol,driver,"€" );
	}

	public boolean validatePoundCurrency(WebDriver driver)
	{
		//selenium.click(home);
		selenium.click(currencyDropDown);
		selenium.click(currencyPound);
		return selenium.checkCurrency(symbol,driver,"£" );
	}
	
	public boolean validateUSDollarCurrency(WebDriver driver)
	{
		//selenium.click(home);
		selenium.click(currencyDropDown);
		selenium.click(currencyUSDollar);
		return selenium.checkCurrency(symbol,driver,"$" );
	}
}
