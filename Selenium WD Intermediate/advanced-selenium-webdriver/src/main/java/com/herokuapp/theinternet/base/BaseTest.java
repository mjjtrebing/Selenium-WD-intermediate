package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	protected WebDriver driver;
	protected Logger log;

	@Parameters({ "browser" })
	@BeforeMethod
	public void setUp(@Optional("chrome") String browser, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);
		
		
		
		// Create driver
		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
		driver = factory.createDriver();
		
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		//System.out.println("Close driver");//this is the old
		// way, if used while threads run, it wont keep track of each thread
		// running in the console. Which test is printing this out?
		log.info("Closing Driver");
		
		
		
		// Close browser
		driver.quit();
	}

}
