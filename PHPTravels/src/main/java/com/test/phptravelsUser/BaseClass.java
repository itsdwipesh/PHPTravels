package com.test.phptravelsUser;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.main.phptravelsUser.*;

public class BaseClass
{
	final String browserName="Chrome";//browserName can be IE, Chrome, PhantomJS
	final boolean sendEmail=false;
	static WebDriver driver;
	static ExtentReports extent;
	static ExtentTest log;
	static String testName;
	static Utility util=new Utility();
	static HomePage homepage=new HomePage(driver);
	static LoginPage loginpage=new LoginPage(driver);
	static UserPage userpage=new UserPage(driver);
	static SearchResultsPage searchPage=new SearchResultsPage(driver);
	
	@BeforeClass
	//@Parameters({"browser"})
	void beforeClass()//(String browserName)
	{
		//this.browserName=browserName;
		ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/TestSuit1_"+browserName+".html");
	    extent = new ExtentReports(); 
	    extent.attachReporter(reporter);
	}
	
	@BeforeMethod
	void launchBrowser(Method method, Object[] testData)
	{	
		if (testData.length > 0) 
		{
		     testName=method.getName() + "_" + testData[0];
		}
		else
		{
			testName=method.getName();
		}
			
		log=extent.createTest(testName);
		
		if(browserName.equalsIgnoreCase("IE"))
		{
			driver=util.getIEDriver();
		}
		
		else if(browserName.equalsIgnoreCase("Chrome"))
		{
		driver=util.getCromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("PhantomJS"))
		{
		driver=util.getPhantomJSDriver();
		}
		
		else
		{
			log.error("Browser name is incorrect");
			extent.flush();
			System.exit(0);
		}
		
	}
	
	@DataProvider
	public Object[][] testDataSheet1()
	{
		int lastRow=util.getLastRowExcel("TestData1", "Sheet1");
		int lastCol=util.getLastColExcel("TestData1", "Sheet1");
		
		Object[][] data=new Object[lastRow][1];
		
		Hashtable<String,String> table= null;
		
		for(int row=1; row<=lastRow; row++)
		{
			table=new Hashtable<String,String>();
			for(int col=0; col<lastCol; col++)
			{
			table.put(util.readExcel("TestData1", "Sheet1",0,col), util.readExcel("TestData1", "Sheet1",row,col));
			data[row-1][0]=table;
			}
		}
		return data;	
	}
	
	@DataProvider
	public Object[][] testDataSheet2()
	{
		int lastRow=util.getLastRowExcel("TestData1", "Sheet2");
		Object[][] data=new Object[lastRow][5];
		
		for(int i=1; i<=lastRow; i++)
		{
			data[i-1][0] = util.readExcel("TestData1", "Sheet2",i,0);
			data[i-1][1] = util.readExcel("TestData1", "Sheet2",i,1);
			data[i-1][2] = util.readExcel("TestData1", "Sheet2",i,2);
			data[i-1][3] = util.readExcel("TestData1", "Sheet2",i,3);
			data[i-1][4] = util.readExcel("TestData1", "Sheet2",i,4);
		}
		
		return data;	
	}
	
	@AfterMethod
	void tearDown(ITestResult result) throws IOException
	{	
		String screenName=testName+"("+browserName+")";
		if(result.getStatus()==ITestResult.FAILURE)
		{
			util.getScreen(driver, System.getProperty("user.dir")+"/Reports/Screenshot", screenName);
			log.fail("Test Failed");
			log.fail("Exception is:\n"+result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath("Screenshot/"+screenName+".jpg").build());
		}
		
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			log.pass("Test Passed");
		}
		
		else if(result.getStatus()==ITestResult.SKIP)
		{
			log.skip("Test Skipped");
		}
		
		else
		{
			log.error("Some error occurred!!");
		}
		
		driver.quit();
	}
	
	@AfterClass
	void generateLog()
	{
		extent.flush();
		util.covertToZip("./Reports");
		
		if(sendEmail)
		util.sendMail("itsdwipesh2@gmail.com", "Test Report", "PFA the report", "./Reports.zip", "Test_Report", "itsdwipesh@gmail.com", "9450641439");
	}
}
