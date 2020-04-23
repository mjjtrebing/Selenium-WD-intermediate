package com.herokuapp.theinternet.LoginPageTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class PositiveLoginTests extends TestUtilities {

	@Test
	public void logInTest() {
		log.info("Starting logIn test");

		// Open main Page
		WelcomePageObject wp = new WelcomePageObject(driver, log);
		wp.openPage();

		// Click on formAuthentication page
		LoginPage lp = wp.ClickFormAuthenticationLink();
		// PUT THIS IN(below)
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']//h2[.='Login Page']")).isDisplayed());

		// execute login
		SecureAreaPage securePage = lp.logIn("tomsmith", "SuperSecretPassword!");

		// verify test conditions
		Assert.assertEquals(securePage.getCurrentURL(), securePage.getPageUrl());
		Assert.assertTrue(securePage.isLogOutButtonVisible(), "Logout Button is not currently visible");

		String expectedSuccessMessage = "You logged into a secure area!";
		String actualSuccessMessage = securePage.getSuccessMessageText();
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"Actual success message differs from expected success message. \n\nActual Message: "
						+ actualSuccessMessage + " \nExpected Message: " + expectedSuccessMessage + "\n\n");

		// old way
		/*
		 * // open main page String url = "http://the-internet.herokuapp.com/";
		 * driver.get(url); log.info("Main page is opened.");
		 * 
		 * // Click on Form Authentication link
		 * driver.findElement(By.linkText("Form Authentication")).click();
		 * 
		 * // enter username and password
		 * driver.findElement(By.id("username")).sendKeys("tomsmith");
		 * driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!"
		 * );
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver, 10);//EXPLICIT WAIT
		 * SET UP
		 * 
		 * // push log in button WebElement logInButton =
		 * driver.findElement(By.className("radius"));
		 * wait.until(ExpectedConditions.elementToBeClickable(logInButton));//
		 * EXPLICIT WAIT USE logInButton.click();
		 * 
		 * // verifications // new url String expectedUrl =
		 * "http://the-internet.herokuapp.com/secure";
		 * Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		 * 
		 * // log out button is visible Assert.assertTrue(driver.findElement(By.
		 * xpath("//a[@class='button secondary radius']")).isDisplayed(),
		 * "logOutButton is not visible.");
		 * 
		 * // Successful log in message String expectedSuccessMessage =
		 * "You logged into a secure area!"; String actualSuccessMessage =
		 * driver.findElement(By.id("flash")).getText();
		 * Assert.assertTrue(actualSuccessMessage.contains(
		 * expectedSuccessMessage),
		 * "actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
		 * + expectedSuccessMessage + "\nactualSuccessMessage: " +
		 * actualSuccessMessage);
		 * 
		 * // Close browser
		 */
	}
}
