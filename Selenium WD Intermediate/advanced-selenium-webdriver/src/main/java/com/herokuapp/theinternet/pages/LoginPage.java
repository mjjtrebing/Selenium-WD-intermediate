package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {

	
	
	private By usernameLocator = By.id("username");
	private By pwdLocator = By.id("password");
	private By loginButtonLocator = By.tagName("button");
	private By errorMessageLocator = By.id("flash");

	public LoginPage(WebDriver driver, Logger log)
	{
		super(driver, log);
	}

	public SecureAreaPage logIn(String username, String password)
	{
		log.info("Executing logIn with Username: [" + username + "] and password : [" + password + "]");
		//driver.findElement(usernameLocator).sendKeys(username);
		type(username, usernameLocator);
		
		
		//driver.findElement(pwdLocator).sendKeys(password);
		type(password, pwdLocator);
		
		
		//driver.findElement(loginButtonLocator).click();
		click(loginButtonLocator);
		
		return new SecureAreaPage(driver, log);
	}
	
	public void negativeLoginAttempt(String username, String password)
	{
		log.info("Executing bad login attempt with Username: [" + username + "] and password : [" + password + "]");
		type(username, usernameLocator);
		type(password, pwdLocator);
		click(loginButtonLocator);
		
	}
	
	public String getFailBanner()
	{
		return find(errorMessageLocator).getText();
	}
}
