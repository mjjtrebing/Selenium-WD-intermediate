package com.herokuapp.theinternet.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBoxPage extends BasePageObject {

	private By checkBoxLocator = By.xpath("//input[@type='checkbox']");

	public CheckBoxPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/**
	 * get list of all checkboxes and check if unchecked
	 */
	public void selectAllCheckboxes() {
		log.info("checking all uncehcked boxes");
		List<WebElement> checkboxes = findAll(checkBoxLocator);
		for (WebElement checkBoxLocator : checkboxes) {
			if (!checkBoxLocator.isSelected()) {
				checkBoxLocator.click();// this method is from selenium
			}
		}

	}
	
	/**
	 * verify all check boxes are indeed checkedS
	 * @return boolean
	 */
	public boolean areAllCheckboxesChecked()
	{
		boolean ischecked = false;
		log.info("verify that all checkboxes are checked");
		List<WebElement> checkboxes = findAll(checkBoxLocator);
		for (WebElement checkBoxLocator : checkboxes) {
			if (checkBoxLocator.isSelected()) {
				ischecked = true;
			}
		}
		return ischecked;
	}
}
