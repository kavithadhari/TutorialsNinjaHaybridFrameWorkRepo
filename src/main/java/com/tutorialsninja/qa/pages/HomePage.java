package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
   @FindBy(xpath="//span[text()='My Account']")
	private WebElement MyAccountDropMenu;
   
   @FindBy(linkText="Login")
   private WebElement LoginOption;
   
   @FindBy(linkText="Register")
   private WebElement RegisterOption;
   
   @FindBy(name="search")
   private WebElement SearchBoxField;
   
   @FindBy(xpath="//div[@id='search']/descendant::button")
   private WebElement SearchButton;
   
   public HomePage(WebDriver driver)
   {
	 this.driver=driver;  
	 
	 PageFactory.initElements(driver, this);
   }
   
   //Actions
   public SearchPage clickonSearchButton()
   {
	   SearchButton.click();
	   return new SearchPage(driver);
   }
   
   public SearchPage searchForAProduct(String Productname)
   {
	   SearchBoxField.sendKeys(Productname);
	   SearchButton.click();
	   return new SearchPage(driver);
   }
   
   public void EnterProductNameToSearch(String Productname)
   {
	   SearchBoxField.sendKeys(Productname);
   }
   public void clickonMyAccount()
   {
	   MyAccountDropMenu.click();
   }
   
   public LoginPage selectLoginoption()
   {
	   LoginOption.click();
	   return new LoginPage(driver);
   }
   
   public LoginPage navigateToLogin()
   {
	   MyAccountDropMenu.click();
	   LoginOption.click();
	   return new LoginPage(driver);
   }
   
   public RegisterPage selectRegisterOption()
   {
	   RegisterOption.click();
	   return new RegisterPage(driver);
   }
   
   public RegisterPage navigateToRegistration()
   {
	   MyAccountDropMenu.click();
	   RegisterOption.click();
	   return new RegisterPage(driver);
   }
}
