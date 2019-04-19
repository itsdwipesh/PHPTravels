package com.main.phptravelsUser;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


public class HomePage
{	
	WebDriver driver;
	Utility util=new Utility();
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public HomePage openHomePage(WebDriver driver)
	{
		try {
		driver.get(util.getValueFromProperty("HomePage","URL"));
		driver.manage().window().maximize();
			Thread.sleep(1000);
			} 
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		return this;
	}
	
	public HomePage clickMyAccount(WebDriver driver)
	{
		//driver.findElement(By.xpath(util.getValueFromProperty("HomePage","MYACCOUNT"))).click();
		util.highlightElement(driver,By.xpath(util.getValueFromProperty("HomePage","MYACCOUNT"))).click();
		return this;
	}
	
	public HomePage clickLogin(WebDriver driver)
	{
		//driver.findElement(By.xpath(util.getValueFromProperty("HomePage","Login"))).click();
		util.highlightElement(driver,By.xpath(util.getValueFromProperty("HomePage","Login"))).click();
		return this;
	}
	
	public String homePageExpectedTitle()
	{
		return (util.getValueFromProperty("HomePage","title"));
	}

}
