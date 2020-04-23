package com.herokuapp.theinternet.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class basically handles all interactions with the driver, It also cuts
 * down on lengthy code lines like...
 * --driver.findElement(pwdLocator).sendKeys(password); OR
 * --driver.findElement(loginButtonLocator).click(); by replacing them with
 * methods like type(), or click()
 * 
 * @author mjjtr
 *
 */
public class BasePageObject {

	protected WebDriver driver;
	protected Logger log;

	public BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	protected void openUrl(String url) {
		driver.get(url);
	}

	// This method finds a single webelment an returns it
	protected WebElement find(By locator) {
		waitForVisibilityOf(locator, 5);
		// I put the wait here after the tests started failing.
		return driver.findElement(locator);
	}

	// This method finds all elements and returns as a list
	protected List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}

	protected void click(By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).click();
	}

	/**
	 * gets current URL, This method must be public because it will be used
	 * during tests which is not directly related to this class
	 * 
	 * @return current URL
	 */
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	/** Get title of current page */
	public String getCurrentPageTitle() {
		return driver.getTitle();
	}

	/** Gets the actual source code of the page */
	public String getCurrentPageSource() {
		return driver.getPageSource();
	}

	protected void type(String text, By locator) {
		waitForVisibilityOf(locator, 5);
		// waitForVisibilityOf(locator);this will wait 30s if triggered
		find(locator).sendKeys(text);
	}

	/**
	 * wait for specific ExpectedCondition for a given amount of time(seconds)
	 * 
	 * if timeOutInSeconds is null when given, then timeOutInSeconds is set to
	 * 30 seconds
	 * 
	 * @param condition
	 * @param timeOutInSeconds
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		// look all this crap up
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for a given number of seconds for element with given locator to be
	 * visible on page
	 * 
	 * this method waits for locator to be visible twice for a given number of
	 * seconds
	 * 
	 * @param locator
	 * @param timeOutInSeconds
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds)
	// I think Integer... is optional param
	{
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
			}
		}
	}

	/** Wait for alert present and then switch to it */
	// sets up a mini driver specifically for the alert window
	protected Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert();
	}

	/*
	 * This method Grabs the current tab identifier, then grabs all the other
	 * tab identifiers and puts them in a SET this set then generates a unique
	 * iterator. it then iterates through the set comparing tab Titles to the
	 * expected one
	 */
	public void switchToWindowWithTitle(String expectedTitle) {
		// Switching to new window
		String firstWindow = driver.getWindowHandle();// grab current window
														// Identifier

		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> windowsIterator = allWindows.iterator();

		while (windowsIterator.hasNext()) {
			String windowHandle = windowsIterator.next().toString();
			if (!windowHandle.equals(firstWindow)) {
				driver.switchTo().window(windowHandle);
				if (getCurrentPageTitle().equals(expectedTitle)) {
					break;
				}
			}
		}
	}

	/** Switch to iFrame using it's locator */
	protected void switchToFrame(By frameLocator) {
		driver.switchTo().frame(find(frameLocator));
	}

	/** Press Key on locator */
	protected void pressKey(By locator, Keys key) {
		find(locator).sendKeys(key);
	}

	/** Press Key using Actions class */
	public void pressKeyWithActions(Keys key) {
		log.info("Pressing " + key.name() + " using Actions class");
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();
	}

	/** Perform scroll to the bottom */
	public void scrollToBottom() {
		log.info("Scrolling to the bottom of the page");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/** Drag 'from' element to 'to' element */
	protected void performDragAndDrop(By from, By to) {
		// Actions action = new Actions(driver);
		// action.dragAndDrop(find(from), find(to)).build().perform();

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(
				"function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
						+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
						+ "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
						+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
						+ "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
						+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
						+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
						+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n"
						+ "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
						+ "var dragStartEvent =createEvent('dragstart');\n"
						+ "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
						+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
						+ "var dragEndEvent = createEvent('dragend');\n"
						+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
						+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
						+ "simulateHTML5DragAndDrop(source,destination);",
				find(from), find(to));
	}

	/** Perform mouse hover over element */
	protected void hoverOverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

}
