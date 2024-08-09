package testclasses;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pomclasses.Account;
import pomclasses.HomePage;
import qa.base.Basee;
import qa.util.Utility;

public class SignIn extends Basee {

	public WebDriver driver;
	HomePage homePage;
	Account account;
	
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
		account = new Account(driver);
		driver.get(prop.getProperty("url"));
	}
	
//	@BeforeMethod
//	public void openApplication()
//	{
//		driver.get("https://www.netflix.com/in/");
//	}
	
	@Test
	public void clickAccountAndCheckSignUpPageUrl()
	{
		homePage.clickOnAccount();
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.netflix.com/login?nextpage=https%3A%2F%2Fwww.netflix.com%2Fyouraccount";
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test(dataProvider="dat")
	public void checkErrorMessageWhenSendInvalidCredential(String email, String pass)
	{
		homePage.clickOnAccount();
		account.sendEmail(email);
		account.sendPass(pass);
//		homePage.clickOnAccount();
//		account.sendEmail("kaido@gmail.com");
//		account.sendPass("kaido124");
//		String actualMessage = account.clickOnSignInButtonAndGetErrorMessage();
//		String expectedMessage = "Incorrect password";
//		Assert.assertEquals(actualMessage, expectedMessage);
		
	}
	
	@DataProvider(name="dat")
	public Object getData() throws IOException
	{
		//Object[] data = {"kaido@gmail.com","kaido124"};
		//Object[][] data = {{"kaido@gmail.com","kaido124"},{"zoro@gmail.com","zoro124"}};
		Object[][] data = Utility.getTestDataFromexcel("net");
		return data;
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
