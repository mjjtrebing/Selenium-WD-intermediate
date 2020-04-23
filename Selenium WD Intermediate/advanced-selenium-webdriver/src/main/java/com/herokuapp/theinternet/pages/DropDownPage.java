package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.herokuapp.theinternet.base.BaseTest;

public class DropDownPage extends BasePageObject {

	private By dropdown = By.id("dropdown");

	public DropDownPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/**
	 * This method selects the given option from a drop down menu
	 * 
	 * @param i
	 */
	public void selectOption(int i) {
		log.info("selecting option " + i + " from dropdown");
		WebElement dropdownElement = find(dropdown);
		Select dropdown = new Select(dropdownElement);

		// there are three ways to use select class
		// #1
		// dropdown.selectByIndex(i)

		// #2
		// This selects the option based on the value of the drop down
		dropdown.selectByValue("" + i);

		// #3
		// this slects the text in the DD menu
		// dropdown.selectByVisibleText("Option " i);

		// #4
		// You dont need the select class sometimes, you can simply use the
		// click()
		// provided by selenium

	}

	public String getSelected() {
		WebElement dropdownElement = find(dropdown);
		Select dropdown = new Select(dropdownElement);
		String selectedOption = dropdown.getFirstSelectedOption().getText();
		log.info(selectedOption + " is selected in dropdown");
		return selectedOption;

	}
}
