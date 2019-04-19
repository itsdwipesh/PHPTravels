package com.test.phptravelsUser;

import java.util.Enumeration;
import java.util.Hashtable;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class TestSuit1 extends BaseClass
{
	
	@Test(enabled=true)
	void testCase1()
	{
		log.info("launch Browser");
		
		homepage.openHomePage(driver);
		log.info("Open Home Page");
		
		String actualTitle=driver.getTitle();
		String expectedTitle=homepage.homePageExpectedTitle();
		
		Assert.assertEquals(expectedTitle,actualTitle);
		log.info("Verify Title");
		
		//throw new SkipException("skipped");
	   }
	
	@Test(enabled=false)//(dependsOnMethods = { "testCase1" })
	void testCase2() throws InterruptedException
	{
		log.info("launch Browser");
		
		homepage.openHomePage(driver);
		log.info("Open Home Page");
		
		homepage.clickMyAccount(driver);
		log.info("Click My Account");
		
		homepage.clickLogin(driver);
		log.info("Click Login");

		Thread.sleep(1500);
		log.info("Login Page Opens");
		
		loginpage.enterEmail(driver,util.getValueFromProperty("Config","Valid_email"));
		log.info("Enter Email");
		
		loginpage.enterPassword(driver,util.getValueFromProperty("Config","Valid_password"));
		log.info("Enter Password");
		
		loginpage.clickLogin(driver);
		log.info("Click Login");
		
		Thread.sleep(2000);
		
		String expectedTitle="My Account1";
		String actualTitle=driver.getTitle();
		
		Assert.assertEquals(actualTitle,expectedTitle);
		log.info("Verify Title");
	}
	
	@Test(dataProvider="testDataSheet1",enabled=false)
	void testCase3(Hashtable<String,String> data) throws InterruptedException
	{
		log.info("launch Browser");
		
		homepage.openHomePage(driver);
		log.info("Open Home Page");
		
		homepage.clickMyAccount(driver);
		log.info("Click My Account");
		
		homepage.clickLogin(driver);
		log.info("Click Login");

		Thread.sleep(1500);
		log.info("Login Page Opens");
		
		loginpage.enterEmail(driver,data.get("Email"));
		log.info("Enter Email :"+data.get("Email"));
		
		loginpage.enterPassword(driver,data.get("Password"));
		log.info("Enter Password :"+data.get("Password"));
		
		loginpage.clickLogin(driver);
		log.info("Click Login");
		
		Thread.sleep(1500);
		
		String expectedTitle="My Account";
		String actualTitle=driver.getTitle();
		
		Assert.assertEquals(actualTitle,expectedTitle);
		log.info("Verify Title");
	}

	@Test(dataProvider="testDataSheet2",enabled=false)
	void testCase4(String cityName, String checkin, String checkout, String hotelName, String expectedAmount) throws InterruptedException
	{
		log.info("launch Browser");
		
		homepage.openHomePage(driver);
		log.info("Open Home Page");
		
		userpage.enterHotel(driver,cityName);
		log.info("Enter City Name :"+cityName);
		
		userpage.enterCheckinDate(driver,checkin);
		log.info("Enter Check-in date :"+checkin);
		
		userpage.enterCheckoutDate(driver,checkout);
		log.info("Enter Check-out date :"+checkout);
		
		log.info("Check Hotel :"+hotelName);
		
		userpage.clickSearch(driver);
		log.info("Click Search");
		
		Thread.sleep(1500);
		
		String actualAmount=searchPage.getAmount(driver, hotelName).substring(1);
		
		Assert.assertEquals(actualAmount, expectedAmount);
		log.info("Verify Hotel Amount");
	}
	
	@Test(enabled=false)
	void imageTestClickFlight() throws Exception
	{
		Screen screen = new Screen();
		homepage.openHomePage(driver);
		
		Pattern flights = new Pattern(".\\Screenshot\\TestImage\\FLIGHTS.png");
		screen.wait(flights, 10);
		screen.click(flights);
		Thread.sleep(1000);
		
		Pattern tours = new Pattern(".\\Screenshot\\TestImage\\TOURS.png");
		screen.wait(tours, 10);
		screen.click(tours);
		
		Thread.sleep(1000);
	}
	
}
