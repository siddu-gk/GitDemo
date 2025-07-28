package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//	@FindBy(css="div[class='actions'] a")
//	WebElement placeOrderTab;
	
	public void selectCountry(String name)
	{
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(name);
		List<WebElement> country = driver.findElements(By.cssSelector("div[class='form-group'] button"));
		
		for(WebElement w:country)
		{
			if(w.getText().equalsIgnoreCase(name)) {
				w.click();
				break;
			}
			
		}
		
	}
	
	public void placeOrder()
	{
		driver.findElement(By.cssSelector("div[class='actions'] a")).click();
		//placeOrderTab.click();
	}
	
	public String confirmationPageMessage() throws InterruptedException
	{
		String message=driver.findElement(By.xpath("//tr/td/h1[@class='hero-primary']")).getText();
		return message;
	}
	
	
}
