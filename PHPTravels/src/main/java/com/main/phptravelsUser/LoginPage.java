package com.main.phptravelsUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage 
{
	WebDriver driver;
	Utility util=new Utility();
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public LoginPage enterEmail(WebDriver driver,String email)
	{
		//driver.findElement(By.xpath(util.getValueFromProperty("LoginPage","Email"))).sendKeys(email);
		util.highlightElement(driver,By.xpath(util.getValueFromProperty("LoginPage","Email"))).sendKeys(email);
		return this;
	}
	
	public LoginPage enterPassword(WebDriver driver,String password)
	{
		//driver.findElement(By.xpath(util.getValueFromProperty("LoginPage","Password"))).sendKeys(password);
		util.highlightElement(driver,By.xpath(util.getValueFromProperty("LoginPage","Password"))).sendKeys(password);
		return this;
	}
	
	public LoginPage clickLogin(WebDriver driver)
	{
		//driver.findElement(By.xpath(util.getValueFromProperty("LoginPage","LOGIN"))).click();
		util.highlightElement(driver,By.xpath(util.getValueFromProperty("LoginPage","LOGIN"))).click();
		return this;
	}
	
	public String loginPageExceptedTitle()
	{
		return (util.getValueFromProperty("LoginPage","title"));
	}
}
