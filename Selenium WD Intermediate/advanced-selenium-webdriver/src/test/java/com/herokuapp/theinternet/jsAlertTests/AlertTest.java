package com.herokuapp.theinternet.jsAlertTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DropDownPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import com.herokuapp.theinternet.pages.jsAlertPage;

public class AlertTest extends TestUtilities {

	@Test
	public void jsAlertTest() {

		WelcomePageObject wp = new WelcomePageObject(driver, log);
		wp.openPage();

		// opens link to alert page
		jsAlertPage alertPage = wp.clickJSAlert();

		// Click alert button
		alertPage.openJSAlert();

		// get alert text
		String alertMessage = alertPage.getAlertText();

		// click OKAY
		alertPage.acceptAlert();

		// get RESULT text
		String result = alertPage.getResultText();

		// verrifications
		// #1 - Alert text is expected
		Assert.assertTrue(alertMessage.equals("I am a JS Alert"), "Alert message is not expected");

		// #2 - result text is expected
		Assert.assertTrue(result.equals("You successfuly clicked an alert"), "result is not what was expected");
	}

	@Test
	public void jsDismissTest() {

		WelcomePageObject wp = new WelcomePageObject(driver, log);
		wp.openPage();

		// opens link to alert page
		jsAlertPage alertPage = wp.clickJSAlert();

		// Click CONFIRM button
		alertPage.openJSConfirm();

		// get alert text
		String alertMessage = alertPage.getAlertText();

		// click CANCEL
		alertPage.dismissAlert();

		// get RESULT text
		String result = alertPage.getResultText();

		// verrifications
		// #1 - Alert text is expected
		Assert.assertTrue(alertMessage.equals("I am a JS Confirm"), "Alert message is not expected");

		// #2 - result text is expected
		Assert.assertTrue(result.equals("You clicked: Cancel"), "result is not what was expected");
	}
	
	
	@Test
	public void jsPromptTest() {

		WelcomePageObject wp = new WelcomePageObject(driver, log);
		wp.openPage();

		// opens link to alert page
		jsAlertPage alertPage = wp.clickJSAlert();

		// Click CONFIRM button
		alertPage.openJSPrompt();

		// get alert text
		String alertMessage = alertPage.getAlertText();
		
		//type text into alert
		alertPage.typeTextIntoAlert("Hello alert, im Matt");


		// get RESULT text
		String result = alertPage.getResultText();

		// verrifications
		// #1 - Alert text is expected
		
		Assert.assertTrue(alertMessage.equals("I am a JS prompt"), "Alert message is not expected");

		// #2 - result text is expected
		Assert.assertTrue(result.equals("You entered: Hello alert, im Matt"), "result is not what was expected");
	}

}
