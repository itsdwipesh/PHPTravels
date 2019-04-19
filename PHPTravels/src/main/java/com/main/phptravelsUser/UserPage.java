package com.main.phptravelsUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage 
{
	WebDriver driver;
	Utility util=new Utility();
	
	public UserPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public UserPage enterHotel(WebDriver driver,String HotelName)
	{
		try {
		//util.highlightElement(driver, By.xpath(util.getValueFromProperty("UserPage","HOTELS"))).click();
		driver.findElement(By.xpath(util.getValueFromProperty("UserPage","HOTELS"))).click();
		
		//util.highlightElement(driver, By.xpath(util.getValueFromProperty("UserPage","HotelInput"))).sendKeys(HotelName);
		driver.findElement(By.xpath(util.getValueFromProperty("UserPage","HotelInput"))).sendKeys(HotelName);
		
		Thread.sleep(1500);
		
		//util.highlightElement(driver, By.xpath(util.getValueFromProperty("UserPage","HotelSelect"))).click();
		driver.findElement(By.xpath(util.getValueFromProperty("UserPage","HotelSelect"))).click();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public UserPage enterCheckinDate(WebDriver driver,String checkinDate)
	{
		//util.highlightElement(driver, By.xpath(util.getValueFromProperty("UserPage","Checkin"))).sendKeys(checkinDate);
		driver.findElement(By.xpath(util.getValueFromProperty("UserPage","Checkin"))).sendKeys(checkinDate);
		return this;
	}
	
	public UserPage enterCheckoutDate(WebDriver driver,String checkoutDate)
	{
		//util.highlightElement(driver,By.xpath(util.getValueFromProperty("UserPage","Checkout"))).sendKeys(checkoutDate); 
		driver.findElement(By.xpath(util.getValueFromProperty("UserPage","Checkout"))).sendKeys(checkoutDate);
		return this;
	}
	
	public UserPage clickSearch(WebDriver driver)
	{
		//util.highlightElement(driver,By.xpath(util.getValueFromProperty("UserPage","SEARCH"))).click();
		driver.findElement(By.xpath(util.getValueFromProperty("UserPage","SEARCH"))).click();
		return this;
	}
}
