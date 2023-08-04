package com.tutorials.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base

{
	SearchPage searchpage;
	public WebDriver driver;
	HomePage homepage;
	
	public SearchTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver=initializeBrowserandOpenApplication(prop.getProperty("browsername"));
		homepage=new HomePage(driver);
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void VerifySearchPassingValidProduct()
	{
		searchpage=homepage.searchForAProduct(dataprop.getProperty("ValidSearchProduct"));
		Assert.assertTrue(searchpage.displayStatusofproductdisplayed(),"Valid Product hp is not displayed");
		
	}
	
	@Test(priority=2)
	public void VerifySearchPassingInvalidProduct()
	{
		searchpage=homepage.searchForAProduct(dataprop.getProperty("InvalidSearchProduct"));
		Assert.assertEquals(searchpage.retreiveNoproductwarningText(), "abcd","No Procuct Serach Details message is not displayed");
	}
	
	@Test(priority=3,dependsOnMethods= {"VerifySearchPassingValidProduct","VerifySearchPassingInvalidProduct"})
	public void VerifySearchwithoutpassingproduct()
	{
		searchpage=homepage.clickonSearchButton();
		Assert.assertEquals(searchpage.retreiveNoproductwarningText(), dataprop.getProperty("InvalidSearchWarningText"),"No Procuct Serach Details message is not displayed");}
	
}
