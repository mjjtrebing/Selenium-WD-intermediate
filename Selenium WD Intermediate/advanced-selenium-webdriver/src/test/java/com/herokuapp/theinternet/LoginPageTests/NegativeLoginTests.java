package com.herokuapp.theinternet.LoginPageTests;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class NegativeLoginTests extends TestUtilities {

	


	/*@Parameters({ "username", "password", "expectedMessage" })*/
	@Test(priority = 1, dataProvider = "csvReader", dataProviderClass= CsvDataProviders.class)
	public void negativeLoginTest(Map<String, String> testData) {
		//DATA
		String testnum=testData.get("testnum");
		String username=testData.get("username");
		String password=testData.get("password");
		String expectedErrorMessage=testData.get("expectedMessage");
		String description=testData.get("description");

		
		
		log.info("Starting negativeLoginTest Number:" + testnum + "\nfor " + description);

		WelcomePageObject wp = new WelcomePageObject(driver, log);
		wp.openPage();
		LoginPage lp = wp.ClickFormAuthenticationLink();
		
		lp.negativeLoginAttempt(username, password);
		String actualErrorMessage = lp.getFailBanner();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		// open main page
		String url = "http://the-internet.herokuapp.com/";
		driver.get(url);
		log.info("Main page is opened.");

		// Click on Form Authentication link
		driver.findElement(By.linkText("Form Authentication")).click();

		// enter username and password
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		// push log in button
		driver.findElement(By.className("radius")).click();

		// Verification
		String actualErrorMessage = driver.findElement(By.id("flash")).getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
						+ expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);*/
	}



}
