package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject extends BasePageObject {

	private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
	private By checkBoxLinkLocator = By.linkText("Checkboxes");
	private By dropDownLinkLocator = By.linkText("Dropdown");
	private By alertLinkLocator = By.linkText("JavaScript Alerts");
	private By multipleWindowsLinkLocator = By.linkText("Multiple Windows");
	private By editorLinkLocator = By.linkText("WYSIWYG Editor");

	protected String pageURL = "https://the-internet.herokuapp.com/";

	public WelcomePageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void openPage() {
		log.info("Opening page: " + pageURL);
		openUrl(pageURL);
		log.info("page OPEN!!");
	}

	public LoginPage ClickFormAuthenticationLink() {
		log.info("Clicking form Authentication Link on Welcome Page");
		click(formAuthenticationLinkLocator);

		return new LoginPage(driver, log);

	}

	public CheckBoxPage clickCheckBoxPage() {
		log.info("Clicking checkbox on Welcome Page");
		click(checkBoxLinkLocator);

		return new CheckBoxPage(driver, log);// BUILD THE CHECKBOX PAGE COPY
												// OTHER POM PAGE
	}

	public DropDownPage clickDropDownLink() {
		log.info("Clicking drop down link on Welcome Page");
		click(dropDownLinkLocator);

		return new DropDownPage(driver, log);
	}

	public jsAlertPage clickJSAlert() {
		log.info("Clicking Javascript Alert link on Welcome Page");
		click(alertLinkLocator);

		return new jsAlertPage(driver, log);
	}

	public WindowsPage clickMultipleWindowsLink() {
		log.info("Clicking Multiple Windows link on Welcome Page");
		click(multipleWindowsLinkLocator);
		return new WindowsPage(driver, log);
	}
	
	public EditorPage clickWYSIWYGEditorLink() {
		log.info("Clicking WYSIWYG Editor link on Welcome Page");
		click(editorLinkLocator);
		return new EditorPage(driver, log);
	}

}
