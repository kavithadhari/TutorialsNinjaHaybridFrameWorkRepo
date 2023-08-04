package com.tutorialsninja.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.Utils.Utilities;

public class Base

{
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public  Base()
	{
		prop=new Properties();
		File propfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		dataprop=new Properties();
		File datapropfile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try
		{
		FileInputStream dataFis=new FileInputStream(datapropfile);
		dataprop.load(dataFis);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		try
		{
			FileInputStream fis=new FileInputStream(propfile);
			prop.load(fis);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		
	}
	public WebDriver initializeBrowserandOpenApplication(String browsername)
	
	{

		if(browsername.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			driver= new EdgeDriver();
		}
		else if(browsername.equalsIgnoreCase("safari"))
		{
			driver=new SafariDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));	
		return driver;
	}
	
	
}
