package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class jsAlertPage extends BasePageObject {
	
	private By clickForJSAlertButtonLocator = By.xpath("//div[@id='content']//ul//button[.='Click for JS Alert']");
	private By clickForJSConfirmButtonLocator = By.xpath("//div[@id='content']//ul//button[.='Click for JS Confirm']");
	private By clickForJSPromptButtonLocator = By.xpath("//div[@id='content']//ul//button[.='Click for JS Prompt']");
	private By resultTextLocator = By.id("result");
	
	public jsAlertPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	public void openJSAlert()
	{
		log.info("clicking on 'click on js alert' button to open alert");
		click(clickForJSAlertButtonLocator);
	}
	
	public void openJSConfirm()
	{
		log.info("clicking on 'click on js alert' button to open alert");
		click(clickForJSConfirmButtonLocator);
	}
	
	public void openJSPrompt()
	{
		log.info("clicking on 'click on js alert' button to open alert");
		click(clickForJSPromptButtonLocator);
	}
	
	public String getAlertText()
	{
		Alert alert = switchToAlert();
		String alertText = alert.getText();
		log.info("Alert says: " + alertText);
		return alertText;
		
	}
	
	public void acceptAlert(){
		log.info("Switching to alert and pressing OK");
		Alert alert = switchToAlert();
		alert.accept();
	}
	
	public void dismissAlert()
	{
		log.info("switch to alert and pressing cancel");
		Alert alert = switchToAlert();
		alert.dismiss();
	}
	
	public void typeTextIntoAlert(String text)
	{
		log.info("typeing text into alert and pressing OK");
		Alert alert = switchToAlert();
		alert.sendKeys(text);
		alert.accept();
	}
	
	public String getResultText()
	{
		String result = find(resultTextLocator).getText();
		log.info("result text: " + result);
		return result;
	}
	

	
	
	
	
	
	
	
	
	
	
}
