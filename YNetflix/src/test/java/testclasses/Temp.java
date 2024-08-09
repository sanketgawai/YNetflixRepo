package testclasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Temp {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.netflix.com/in/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		WebElement languageDropDown = driver.findElement(By.xpath("//select[@id=':R5ajalalbd:']"));
		Select s = new Select(languageDropDown);
		s.selectByVisibleText("हिन्दी");
		
		WebElement hindiText = driver.findElement(By.xpath("//div[@id='appMountPoint']/descendant::h1"));
		String actualtxt = hindiText.getText();
		String expectedtxt = "अनलिमिटेड फ़िल्में, टीवी शो के साथ भी बहुत कुछ";
		if(actualtxt.equalsIgnoreCase(expectedtxt))
		{
			System.out.println("title has been change to hindi : true");
		}
		else
		{
			System.out.println("title has been change to hindi : false");
		}
		
		languageDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id=':R5ajalalbd:']")));


        s = new Select(languageDropDown);
        s.selectByVisibleText("English");
		
        WebElement englishText = driver.findElement(By.xpath("//div[@id='appMountPoint']/descendant::h1"));
        //WebElement englishText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='appMountPoint']/descendant::h1")));
        String actualtx = englishText.getText();
        String expectedtx = "Unlimited movies, TV shows and more";
        if(actualtx.equalsIgnoreCase(expectedtx))
        {
        	System.out.println("title has been change to english : true");
        }
        else
        {
        	System.out.println("title has been change to english : false");
        }
        
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,5000)");
        
        WebElement account = driver.findElement(By.xpath("//a[text()='Account']"));
        account.click();
		
        WebElement email = driver.findElement(By.xpath("//input[@id=':r0:']"));
        //js.executeScript("arguments[0].value='kaido@gmail.com'", account);
        email.sendKeys("kaido@gmail.com");
        
        WebElement pass = driver.findElement(By.xpath("//input[@id=':r3:']"));
        pass.sendKeys("kaido124");
        
        WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        System.out.println("sign in button is Enable : "+signInButton.isEnabled());
        
        signInButton.click();
        
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'Incorrect password for kaido@gmail.com')]"));
        errorMessage.getText();
        System.out.println(errorMessage.getText());
        String txt = errorMessage.getText().split("for")[0].trim();
        System.out.println(txt); 
	}
}
