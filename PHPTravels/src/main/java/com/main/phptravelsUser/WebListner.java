package com.main.phptravelsUser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebListner extends AbstractWebDriverEventListener
{
	public void beforeClickOn(WebElement element, WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver; 
		 
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		
		try 
		{
		Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
		 
		System.out.println(e.getMessage());
		} 
		 
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
		
	}
	
}
