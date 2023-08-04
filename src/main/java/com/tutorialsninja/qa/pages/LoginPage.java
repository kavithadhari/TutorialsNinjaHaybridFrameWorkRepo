package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{

	WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement EmailAddressField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement PasswordAddressField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement LoginButtonField;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement EmailPasswordNotMatchingWarningMessage;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void enterEmailAddress(String email)
	{
		EmailAddressField.sendKeys(email);
	}
	public void enterPasswordfield(String password)
	{
		PasswordAddressField.sendKeys(password);
	}
	public AccountPage clickLoginButton()
	{
		LoginButtonField.click();
		return new AccountPage(driver);
	}
	
	public AccountPage login(String email,String password)
	{
		EmailAddressField.sendKeys(email);
		PasswordAddressField.sendKeys(password);
		LoginButtonField.click();
		return new AccountPage(driver);
	}
	
	public String retreiveEmailPasswordNotMatchingwarningmessagetext()
	{
		String WarningMessageText=EmailPasswordNotMatchingWarningMessage.getText();
		return WarningMessageText;
	}
}
