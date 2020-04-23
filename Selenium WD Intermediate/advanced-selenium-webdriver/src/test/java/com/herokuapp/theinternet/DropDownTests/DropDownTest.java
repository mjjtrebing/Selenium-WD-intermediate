package com.herokuapp.theinternet.DropDownTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DropDownPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class DropDownTest extends TestUtilities{

	
	@Test
	public void optionTwoTest()
	{
		WelcomePageObject wp = new WelcomePageObject(driver, log);
		wp.openPage();
		
		DropDownPage dp = wp.clickDropDownLink();
		
		dp.selectOption(2);
				
	}

}
