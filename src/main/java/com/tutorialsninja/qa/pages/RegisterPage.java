package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	@FindBy(id="input-firstname")
	private WebElement FirstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement LastNameField;
	
	@FindBy(id="input-email")
	private WebElement RegisterInputEmail;
	
	@FindBy(id="input-telephone")
	private WebElement RegisterInputTelephone;
	
	@FindBy(id="input-password")
	private WebElement RegisterInputPassword;
	
	@FindBy(id="input-confirm")
	private WebElement RegisterInputConfirmPassword;
	
	@FindBy(name="agree")
	private WebElement PrivacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement RegisterContinueClick;
	
	@FindBy(xpath="//input[@name='newsletter'][@value=1]")
	private WebElement YesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement ExistingEmailRegisterWarningText;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicywarningMessage;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement FirstNameWarningMessage;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement LastNameWarningMessage;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement EmailRegisterWarningMessage;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement TelephoneRegisterWarningMessage;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement PasswordRegisterWarningMessage;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public String retreiveprivacypolicywarningmessage()
	{
		String ActualprivacyPolicywarningMessage=privacyPolicywarningMessage.getText();
		return ActualprivacyPolicywarningMessage;
	}
	
	public String retreiveRegisterNullFirstNameWarningMessage()
	{
		String ActualRegisterFirstNameNullwarningMessage=FirstNameWarningMessage.getText();
		return ActualRegisterFirstNameNullwarningMessage;
	}
	
	public String retreiveRegisterNullLastNameWarningMessage()
	{
		String ActualRegisterLastNameNullwarningMessage=LastNameWarningMessage.getText();
		return ActualRegisterLastNameNullwarningMessage;
	}
	
	public String retreiveRegisterNullEmailWarningMessage()
	{
		String ActualRegisterEmailNullwarningMessage=EmailRegisterWarningMessage.getText();
		return ActualRegisterEmailNullwarningMessage;
	}
	
	public String retreiveRegisterNullTelephoneWarningMessage()
	{
		String ActualRegisterTelephoneNullwarningMessage=TelephoneRegisterWarningMessage.getText();
		return ActualRegisterTelephoneNullwarningMessage;
	}
	
	public String retreiveRegisterNullPasswordWarningMessage()
	{
		String ActualRegisterPasswordNullwarningMessage=PasswordRegisterWarningMessage.getText();
		return ActualRegisterPasswordNullwarningMessage;
	}
	
	public void enterRegisterFirstName(String FirstNameText)
	{
		FirstNameField.sendKeys(FirstNameText);
	}
	public void enterReisterLastName(String LastNameText)
	{
		LastNameField.sendKeys(LastNameText);
	}
	
	public void enterRegisterEmail(String EmailText)
	{
		RegisterInputEmail.sendKeys(EmailText);
	}
	
	public void enterRegisterTelephone(String TelePhoneno)
	{
		RegisterInputTelephone.sendKeys(TelePhoneno);
	}
	
	public void enterRegisterPassword(String PasswordText)
	{
		RegisterInputPassword.sendKeys(PasswordText);
	}
	
	public void enterRegisterConfirmPassword(String ConfirmPasswordText)
	{
		RegisterInputConfirmPassword.sendKeys(ConfirmPasswordText);
	}
	
	public void selectprivacyPolicyClick()
	{
		PrivacyPolicyField.click();
	}
	
	public AccountSuccessPage clickonRegisterContinueclick()
	{
		RegisterContinueClick.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsLetterOption()
	{
		YesNewsLetterOption.click();
	}
	
	public String retreiveExistingEmailRegisterWarningText()
	{
		String ActualExistingEmailRegisterWarningText=ExistingEmailRegisterWarningText.getText(); 
		return ActualExistingEmailRegisterWarningText;
	}
	
	public AccountSuccessPage registerwithMandatoryFields(String FirstNameText ,String LastNameText,String EmailText,String TelePhoneno,String PasswordText)
	{
		FirstNameField.sendKeys(FirstNameText);
		LastNameField.sendKeys(LastNameText);
		RegisterInputEmail.sendKeys(EmailText);
		RegisterInputTelephone.sendKeys(TelePhoneno);
		RegisterInputPassword.sendKeys(PasswordText);
		RegisterInputConfirmPassword.sendKeys(PasswordText);
		PrivacyPolicyField.click();
		RegisterContinueClick.click();
		return new AccountSuccessPage(driver);
		
	}
	public AccountSuccessPage registerwithAllFields(String FirstNameText ,String LastNameText,String EmailText,String TelePhoneno,String PasswordText)
	{
		FirstNameField.sendKeys(FirstNameText);
		LastNameField.sendKeys(LastNameText);
		RegisterInputEmail.sendKeys(EmailText);
		RegisterInputTelephone.sendKeys(TelePhoneno);
		RegisterInputPassword.sendKeys(PasswordText);
		RegisterInputConfirmPassword.sendKeys(PasswordText);
		YesNewsLetterOption.click();
		PrivacyPolicyField.click();
		RegisterContinueClick.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public boolean displayStatusofWarningMessages(String expectedprivacypolicywarningmessage,String expectedFirstNameWarningMessage,String expectedLastNameWarningMessage,String expectedEmailWarningMessage,String expectedTelephoneWarningMessage,String expectedPasswordWarningMessage)
	{
		boolean privacyPolicyWarningStatus=privacyPolicywarningMessage.getText().contains(expectedprivacypolicywarningmessage);
		boolean firstNameWarningStatus=FirstNameWarningMessage.getText().equals(expectedFirstNameWarningMessage);
		boolean lastNameWarningStatus=LastNameWarningMessage.getText().equals(expectedLastNameWarningMessage);
		boolean emailWarningStatus=EmailRegisterWarningMessage.getText().equals(expectedEmailWarningMessage);
		boolean telephoneWarningStatus=TelephoneRegisterWarningMessage.getText().equals(expectedTelephoneWarningMessage);
		boolean passwordWarningStatus=PasswordRegisterWarningMessage.getText().equals(expectedPasswordWarningMessage);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
	}
}
