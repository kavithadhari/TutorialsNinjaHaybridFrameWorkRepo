package com.tutorials.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.Base;
import com.tutorialsninja.qa.Utils.Utilities;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends Base
{
	AccountSuccessPage accountSuccessPage;
	public WebDriver driver;
	RegisterPage registerPage;
	
	public RegisterTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver=initializeBrowserandOpenApplication(prop.getProperty("browsername"));
		HomePage homepage=new HomePage(driver);
		registerPage=homepage.navigateToRegistration();
		
	}
	
	@AfterMethod()
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void VerifyRegisteringAnAccountwithMandatoryFields()
	{
		accountSuccessPage=registerPage.registerwithMandatoryFields(dataprop.getProperty("FirstName"), dataprop.getProperty("LastName"), Utilities.generateEmailwithTimeStamp(), dataprop.getProperty("TelephoneNumber"), prop.getProperty("validpassword"));	
		Assert.assertEquals(accountSuccessPage.RetreiveAccountSuccessMessageText(), dataprop.getProperty("AccountRegisterSuccessMessage"),"Account is not created Successfully");
	}
	
	@Test(priority=2)
	public void VerifyRegisteringAccountwithAllFields()
	{
		accountSuccessPage=registerPage.registerwithAllFields(dataprop.getProperty("FirstName"), dataprop.getProperty("LastName"), Utilities.generateEmailwithTimeStamp(), dataprop.getProperty("TelephoneNumber"), prop.getProperty("validpassword"));
		Assert.assertEquals(accountSuccessPage.RetreiveAccountSuccessMessageText(), dataprop.getProperty("AccountRegisterSuccessMessage"),"Account is not created Successfully");
	}
	
	@Test(priority=3)
	public void VerifyingRegisteringAccountwithExistingEmailAddress()
	{
		registerPage.registerwithAllFields(dataprop.getProperty("FirstName"), dataprop.getProperty("LastName"), prop.getProperty("validemail"), dataprop.getProperty("TelephoneNumber"), prop.getProperty("validpassword"));
		Assert.assertTrue(registerPage.retreiveExistingEmailRegisterWarningText().contains(dataprop.getProperty("DuplicateEmailWarningMessage")), "Warning Message Regarding Duplicate Email is not Displayed");
	}
	
	@Test(priority=4)
	public void VerifyingRegisteringAccountWithoutFillingAnyDetails()
	{
		registerPage.clickonRegisterContinueclick();
		Assert.assertTrue(registerPage.displayStatusofWarningMessages(dataprop.getProperty("PrivacyPolicyWarning"), dataprop.getProperty("FirstNameWarning"), dataprop.getProperty("LastNameWarning"), dataprop.getProperty("EmailEmptyWarning"), dataprop.getProperty("TelephoneWarningMessage"), dataprop.getProperty("PasswordWarningMessage")), "Warning Message(s) are not displayed");
	}
	}
