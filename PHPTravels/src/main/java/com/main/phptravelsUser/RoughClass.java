package com.main.phptravelsUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RoughClass {

	public static void main(String[] args) throws InterruptedException, EmailException, IOException
	{	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employees", "root", "8090180959");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee;");

			ResultSetMetaData rsmd = rs.getMetaData();
			
			int col=rsmd.getColumnCount();
			
			for(int i=1;i<=col;i++)
			{
				System.out.print(rsmd.getColumnLabel(i)+"|");	
			}
			System.out.println();
			
			while (rs.next()) {
				for(int i=1;i<=col;i++)
				{
					System.out.print(rs.getString(i)+"|");	
				}
				System.out.println();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		//Winedium
		/*
		DesktopOptions options = new DesktopOptions();
		options.setApplicationPath("C:\\Windows\\System32\\openfiles.exe");
				
		WebDriverManager.chromedriver().setup();
		WebDriver webdriver=new ChromeDriver();
		
		webdriver.get("https://davidwalsh.name/demo/multiple-file-upload.php");
		webdriver.findElement(By.xpath("//input[@type='file']")).click();
		
		Thread.sleep(3000);
		
		WiniumDriver driver = new WiniumDriver(new URL("http://localhost:9999"),options);
		
		driver.findElementByName("File name:").sendKeys("\"C:\\Users\\91809\\Downloads\\download.jpg\"");
		Thread.sleep(1000);
	    driver.findElement(By.xpath("//*[@Name='Cancel']//preceding-sibling::*[@Name='Open']")).click();
		*/
	}
}
