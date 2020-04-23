package com.herokuapp.theinternet.base;
import org.testng.annotations.DataProvider;
//This class is for building any additional methods to be used in Neg/Pos login test class
public class TestUtilities extends BaseTest {
	
	//This method is Unrelated to CsvDataProviders class
	@DataProvider(name="fileSource")
	protected static Object[][] files()
	{
		
		
		return new Object[][]{
			{1,"index.html"},
			{2,"Program Humor.jpg"},
			{3,"text.txt"}
		};
		
	}

}
