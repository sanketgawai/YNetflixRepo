package pomclasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

	WebDriver driver;
	JavascriptExecutor js;
	
	@FindBy(xpath="//select[@id=':R5ajalalbd:']")
	private WebElement languageDropDown;
	
	@FindBy(xpath="//div[@id='appMountPoint']/descendant::h1")
	private WebElement hindiText;
	
	@FindBy(xpath="//div[@id='appMountPoint']/descendant::h1")
	private WebElement englishText;
	
	@FindBy(xpath ="//a[text()='Account']")
	private WebElement account;
	
	public HomePage(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(driver, this) is to initialize the WebElements defined in a Page Object class
		//Usage of Annotations: The PageFactory class uses annotations like @FindBy to locate elements on a page. When you call PageFactory.initElements(driver, this), it scans the class (this refers to the current instance of the class) for these annotations and initializes the WebElement fields with the appropriate locators.
		js = (JavascriptExecutor)driver;
	}
	
	public String selectHindiAndVerifyTextChangeToHindi()
	{
		Select s = new Select(languageDropDown);
		s.selectByVisibleText("हिन्दी");
		
		String hinditext = hindiText.getText();
		return hinditext; 
	}
	
	public String selectEnglishAndVerifyTextChangeToEnglish()
	{
		Select s = new Select(languageDropDown);
		s.selectByVisibleText("English");
		
		String englishtext = englishText.getText();
		return englishtext;
	}
	
	public void clickOnAccount()
	{
		js.executeScript("window.scrollBy(0,5000)");
		account.click();
	}
}
