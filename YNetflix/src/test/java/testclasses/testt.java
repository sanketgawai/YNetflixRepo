package testclasses;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pomclasses.HomePage;
import qa.base.Basee;

public class testt extends Basee {

	public WebDriver driver;
	HomePage homePage;
	
	@BeforeTest
	public void openBrowser() throws FileNotFoundException
	{
		loadProperties();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		
	}
	
	@BeforeClass
	public void pomObject()
	{
		homePage = new HomePage(driver);
	}
	
	@BeforeMethod
	public void  openApplication()
	{
		driver.get("https://www.netflix.com/in/");
	}
	
	@Test
	public void checkLanguageChangeToHindi()
	{
		String actualText = homePage.selectHindiAndVerifyTextChangeToHindi();
		System.out.println(homePage.selectHindiAndVerifyTextChangeToHindi());
		String expectedText ="अनलिमिटेड फ़िल्में, टीवी शो के साथ भी बहुत कुछ";
		Assert.assertEquals(actualText, expectedText);
	}
	
	@Test
	public void checkLanguageChangeToEnglish()
	{
		String actualText = homePage.selectEnglishAndVerifyTextChangeToEnglish();
		System.out.println(homePage.selectEnglishAndVerifyTextChangeToEnglish());
		String expectedText = "Unlimited movies, TV shows and more";
		Assert.assertEquals(actualText, expectedText);
	}
	
//	@AfterMethod
//	public void afterMehtod()
//	{
//		System.out.println("after method");
//	}
//	
//	@AfterClass
//	public void makepomclassObjectNull()
//	{
//		homePage = null;
//	}
//	
//	@AfterTest
//	public void closeBrowser()
//	{
//		System.gc();
//		driver.quit();
//	}
}
