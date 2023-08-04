package com.tutorials.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.Base;
import com.tutorialsninja.qa.Utils.Utilities;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class LoginTest extends Base
{
	LoginPage loginpage;
	
	public LoginTest()
	{
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void Setup()
	{
	
		driver=initializeBrowserandOpenApplication(prop.getProperty("browsername"));
		HomePage homepage=new HomePage(driver);
		loginpage=homepage.navigateToLogin();
		
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	
	
    @Test(priority=1,dataProvider="ValidCredentialsSupplier")
	public void verifyLoginwithvalidcredentials(String email,String password)
	{
	AccountPage accountpage = loginpage.login(email, password);
	Assert.assertTrue(accountpage.getDisplaystatusofEdityourAccountinformationOption(),"Edit Your Account Information data not found");
	}
    
    @DataProvider(name="ValidCredentialsSupplier")
    public Object[][] supplytestdata()
    {
    	
    	Object [][] data=Utilities.getTestDataFromExcel("Login") ;
    	//Object [][] data= {{"hsrikumar@gmail.com","12345"},{"hsrikumar1@gmail.com","12345"},{"hsrikumar2@gmail.com","12345"}};
    	return data;
    }
    @Test(priority=2)
    public void verifyLoginwithInvalidCredentials()
    {
    	loginpage.login(Utilities.generateEmailwithTimeStamp(), dataprop.getProperty("invalidpassword"));
    	Assert.assertTrue(loginpage.retreiveEmailPasswordNotMatchingwarningmessagetext().contains(dataprop.getProperty("EmailPasswordNoMatchWarning")), "Expected Warning Message is Not Displayed");	
    }
    
    @Test(priority=3)
    public void verifyLoginwithvalidEmailandInvalidPassword()
    {
    	loginpage.login(prop.getProperty("validemail"), dataprop.getProperty("invalidpassword"));
    	Assert.assertTrue(loginpage.retreiveEmailPasswordNotMatchingwarningmessagetext().contains(dataprop.getProperty("EmailPasswordNoMatchWarning")), "Expected Warning Message is Not Displayed");	
    }
    
    @Test(priority=4)
    public void verifyLoginwithInvalidEmailandValidPassword()
    {
    	loginpage.login(Utilities.generateEmailwithTimeStamp(), prop.getProperty("validpassword"));
    	Assert.assertTrue(loginpage.retreiveEmailPasswordNotMatchingwarningmessagetext().contains(dataprop.getProperty("EmailPasswordNoMatchWarning")), "Expected Warning Message is Not Displayed");	
    }
    @Test(priority=5)
    public void verifyLoginwithoutProvidingCredentails()
    {
    	loginpage.clickLoginButton();
    	Assert.assertTrue(loginpage.retreiveEmailPasswordNotMatchingwarningmessagetext().contains(dataprop.getProperty("EmailPasswordNoMatchWarning")), "Expected Warning Message is Not Displayed");
    	
    }
   
}
