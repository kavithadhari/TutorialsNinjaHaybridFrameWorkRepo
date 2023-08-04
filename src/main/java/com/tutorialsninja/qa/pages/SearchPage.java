package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement validsearchproduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement NoProductWarningMessage;
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusofproductdisplayed()
	{
		boolean hpstatus=validsearchproduct.isDisplayed();
		return hpstatus;
	}
	
	public String retreiveNoproductwarningText()
	{
		String NoproductwarningtextMessage=NoProductWarningMessage.getText();
		return NoproductwarningtextMessage ;
	}
	
}
