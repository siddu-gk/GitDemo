package rahulshettyacademy.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory; 

import rahulshettyacademy.ReusableComponents.ReUsableComponent;

public class LandingPage extends ReUsableComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="#userEmail")
	WebElement user;
	
	@FindBy(css="#userPassword")
	WebElement passd;
	
	@FindBy(css="#login")
	WebElement submit; 
	
	@FindBy(css="div[class*='ng-trigger-flyInOut']")
	WebElement errorMessage; 
	
	//css="div[class*='ng-trigger-flyInOut'] div"
	//WebElement errorMessage;
	
	//WebElement errorMessage = driver.findElement(By.cssSelector(".ng-trigger-flyInOut"));
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public ProductCatalogue loginApplication(String emailaddress,String password) throws InterruptedException
	{
//		driver.findElement(By.cssSelector("#userEmail")).sendKeys(emailaddress);
//		driver.findElement(By.cssSelector("#userPassword")).sendKeys(password);
		user.sendKeys(emailaddress);
		passd.sendKeys(password);
		submit.click();
		//Thread.sleep(3000);
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() //throws InterruptedException
	{
		//errorMessage = driver.findElement(By.cssSelector("div[class*='ng-trigger-flyInOut'] div"));
		waitUntilElementsToAppear(errorMessage);
		//Thread.sleep(3000);
		return errorMessage.getText();
	}
}
