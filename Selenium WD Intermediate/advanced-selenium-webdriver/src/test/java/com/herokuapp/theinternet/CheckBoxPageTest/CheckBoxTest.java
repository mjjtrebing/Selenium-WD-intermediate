package com.herokuapp.theinternet.CheckBoxPageTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.CheckBoxPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class CheckBoxTest extends TestUtilities {
	
	
	@Test
	public void selectingTwoCheckBox ()
	{
		log.info("Starting selectingTwoCheckBox Test");
		
		
		//Opening main Nav page
		WelcomePageObject welcomeobj = new WelcomePageObject(driver, log);
		welcomeobj.openPage();
		
		
		//click on  checkboxes link
		CheckBoxPage checkChallenge = welcomeobj.clickCheckBoxPage();
		
		//select all the check boxes
		checkChallenge.selectAllCheckboxes();
		
		//verify all check boxes are selected
		Assert.assertTrue(checkChallenge.areAllCheckboxesChecked());//if false, throws the test
	}

}
