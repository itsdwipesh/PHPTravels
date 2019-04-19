package com.main.phptravelsUser;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class Utility 
{
	
	public String getValueFromProperty(String FileName,String key)
	{
		Properties pro=new Properties();;
		FileInputStream fis;
		try 
		{
			File src=new File("./Configuration/"+FileName+".property");
			fis = new FileInputStream(src);
			pro.load(fis);
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return pro.getProperty(key);
	}
	
	public WebElement highlightElement(WebDriver driver, By ele)
	{
	
	WebElement element=driver.findElement(ele);
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
	
	return element;
	 
	}
	
	public void getScreen(WebDriver driver,String path,String fileName) 
	{
		String completePath=path+"\\"+fileName+".jpg";
	try {
			 Thread.sleep(1000);
			 File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(src, new File(completePath));
	 	}
 
	 catch (Exception e)
	 {
		 System.out.println(e.getMessage());
	 }
	}
	
	public WebDriver getCromeDriver()
	{
		//String driverPath=this.getValueFromProperty("Config", "ChromeDriver");
		//System.setProperty("webdriver.chrome.driver",driverPath);
		WebDriverManager.chromedriver().setup();
		return (new ChromeDriver());
	}
	
	public WebDriver getIEDriver()
	{
		//String driverPath=this.getValueFromProperty("Config", "IEDriver");
		//System.setProperty("webdriver.ie.driver",driverPath);
		WebDriverManager.iedriver().arch32().setup();
		return (new InternetExplorerDriver());
	}
	
	public WebDriver getPhantomJSDriver()
	{
		//String driverPath=this.getValueFromProperty("Config", "PhantomJS");
		//System.setProperty("phantomjs.binary.path",driverPath);
		WebDriverManager.phantomjs().arch32().setup();
		return (new PhantomJSDriver());
	}
	
	public void printLog(String printMessage)
	{
		Logger logger=Logger.getLogger("LogFile");
		PropertyConfigurator.configure("Log4j.properties");
		logger.info(printMessage);
	}
	
	public String readExcel(String fileName,String sheetName,int row, int column)
	{
		FileInputStream ExcelFile;
		XSSFWorkbook ExcelWBook;
		XSSFSheet ExcelWSheet;
		String value;
		
		DataFormatter formatter = new DataFormatter();
		File src=new File("./TestData/"+fileName+".xlsx");
		
		try
		{
			ExcelFile = new FileInputStream(src);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			Cell cell= ExcelWSheet.getRow(row).getCell(column);
			
			ExcelFile.close();
			
			return formatter.formatCellValue(cell);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return "ERROR!!";
		}
	}
	
	public int getLastRowExcel(String fileName,String sheetName)
	{
		FileInputStream ExcelFile;
		XSSFWorkbook ExcelWBook;
		XSSFSheet ExcelWSheet;
		int value;
		
		File src=new File("./TestData/"+fileName+".xlsx");
		
		try
		{
			ExcelFile = new FileInputStream(src);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			value=ExcelWSheet.getLastRowNum();
			
			ExcelFile.close();
			return value;
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}	
	}
	
	public int getLastColExcel(String fileName,String sheetName)
	{
		FileInputStream ExcelFile;
		XSSFWorkbook ExcelWBook;
		XSSFSheet ExcelWSheet;
		int value;
		
		File src=new File("./TestData/"+fileName+".xlsx");
		
		try
		{
			ExcelFile = new FileInputStream(src);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			value=ExcelWSheet.getRow(1).getLastCellNum();
			
			ExcelFile.close();
			return value;
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}	
	}
	
	public void sendMail(String to, String subject, String msg, String attachmentPath, String attachmentName, String username, String password)
	{
		EmailAttachment attachment = new EmailAttachment();
		
		  attachment.setPath(attachmentPath);
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Report");
		  attachment.setName(attachmentName+".zip");
		  
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("smtp.gmail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator(username, password));
		  email.setSSLOnConnect(true);
		  email.setSubject(subject);
		  try {
			email.addTo(to);
			email.setFrom(username);
			email.setMsg(msg);

			// add the attachment
			email.attach(attachment);

			// send the email
			email.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void covertToZip(String folder)
	{
		File directoryToZip = new File(folder);

		List<File> fileList = new ArrayList<File>();
		getAllFiles(directoryToZip, fileList);
		writeZipFile(directoryToZip, fileList);
	}

	private static void getAllFiles(File dir, List<File> fileList) {
		File[] files = dir.listFiles();
		for (File file : files) {
			fileList.add(file);
			if (file.isDirectory()) {
				getAllFiles(file, fileList);
			}
		}
	}

	private static void writeZipFile(File directoryToZip, List<File> fileList) {

		try {
			FileOutputStream fos = new FileOutputStream(directoryToZip.getName() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
			IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

	
	public boolean verifyLinkActive(String linkUrl)
	{
		boolean connection=false;
        try 
        {
           URL url = new URL(linkUrl);
           
           HttpsURLConnection httpsURLConnect=(HttpsURLConnection)url.openConnection();
           
           httpsURLConnect.setConnectTimeout(3000);
           
           httpsURLConnect.connect();
           
           if(httpsURLConnect.getResponseCode()==HttpsURLConnection.HTTP_ACCEPTED)
           {
               connection = true;
           }
          
           else if(httpsURLConnect.getResponseCode()==HttpsURLConnection.HTTP_NOT_FOUND)  
           {
        	   connection = false;
           }
        } 
        catch (Exception e) 
        {
        	connection = false;
        }
        
        return connection;
    } 
}

