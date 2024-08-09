package testclasses;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomclasses.HomePage;
import qa.base.Basee;

public class CheckLanguage extends Basee {

	public WebDriver driver;
	HomePage homePage;
	
	//@Parameters("browserName")
	@BeforeTest
	public void openBrowser() throws FileNotFoundException
	{
		loadProperties();
		this.driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
	}
	
	@BeforeClass
	public void pomClassObject()
	{
		homePage = new HomePage(driver);
		driver.get(prop.getProperty("url"));
	}
	
//	@BeforeMethod
//	public void openApplication()
//	{
//		driver.get("https://www.netflix.com/in/");
//	}
	
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
