package com.main.phptravelsUser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage
{
	WebDriver driver;
	Utility util=new Utility();
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public SearchResultsPage selectHotel(WebDriver driver, String hotel)
	{
		String hotelXpath=util.getValueFromProperty("SearchResults","HotelName").replaceFirst("HOTEL_NAME",hotel);
		
		//util.highlightElement(driver, By.xpath(hotelXpath)).click();
		driver.findElement(By.xpath(hotelXpath)).click();
		return this;
	}
	
	public String getCurrency(WebDriver driver, String hotel)
	{
		String currencyXpath=util.getValueFromProperty("SearchResults","Currency").replaceFirst("HOTEL_NAME",hotel);
		String hotelXpath=util.getValueFromProperty("SearchResults","HotelName").replaceFirst("HOTEL_NAME",hotel);
		
		WebElement hotelEle=driver.findElement(By.xpath(hotelXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hotelEle);
		//hotelEle=util.highlightElement(driver, By.xpath(hotelXpath));
		hotelEle=driver.findElement(By.xpath(hotelXpath));
		
		WebElement currencyEle=driver.findElement(By.xpath(currencyXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", currencyEle);
		//currencyEle=util.highlightElement(driver, By.xpath(currencyXpath));
		currencyEle=driver.findElement(By.xpath(currencyXpath));
		
		return currencyEle.getText();
		
	}
	
	public String getAmount(WebDriver driver, String hotel)
	{
		String amountXpath=util.getValueFromProperty("SearchResults","Amount").replaceFirst("HOTEL_NAME",hotel);
		String hotelXpath=util.getValueFromProperty("SearchResults","HotelName").replaceFirst("HOTEL_NAME",hotel);
		
		WebElement hotelEle=driver.findElement(By.xpath(hotelXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hotelEle);
		//hotelEle=util.highlightElement(driver, By.xpath(hotelXpath));
		hotelEle=driver.findElement(By.xpath(hotelXpath));
		
		WebElement amountEle=driver.findElement(By.xpath(amountXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", amountEle);
		amountEle=util.highlightElement(driver, By.xpath(amountXpath));
		//amountEle=driver.findElement(By.xpath(amountXpath));
		
		return amountEle.getText();
	}
	
}
