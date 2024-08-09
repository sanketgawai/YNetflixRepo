package pomclasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Account {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	@FindBy(xpath="//input[@id=':r0:']")
	private WebElement email;
	
	@FindBy(xpath="//input[@id=':r3:']")
	private WebElement pass;
	
	@FindBy(xpath="//button[text()='Sign In']")
	private WebElement signInButton;
	
	@FindBy(xpath="//div[contains(text(),'Incorrect password for kaido@gmail.com')]")
	private WebElement errorMessage; 
	
	public Account(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,10);
		js = (JavascriptExecutor)driver;
		
	}
	
	public void sendEmail(String eml)
	{
		email.sendKeys(eml);
	}
	
	public void sendPass(String pas)
	{
		pass.sendKeys(pas);
	}
	
	public String clickOnSignInButtonAndGetErrorMessage()
	{
		signInButton.click();
		
		js.executeScript("arguments[0].scrollIntoView(true);", errorMessage);
		//String erMessage = errorMessage.getText();
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		String erMessage = errorMessage.getText().split("for")[0].trim();
		return erMessage;
	}
	
	
}
