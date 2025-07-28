package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.ReusableComponents.ReUsableComponent;

public class ProductCatalogue extends ReUsableComponent {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cart;
	
	By productListsLocator = By.cssSelector(".mb-3");
	By addToCartButtonLocator = By.cssSelector("button:last-of-type");
	By tostmessage = By.cssSelector(".toast-container");
	By animator = By.cssSelector(".ng-animator");
	
	public List<WebElement> getProdList()
	{
		waitUntilElementsToAppear(productListsLocator);
		return products;
	}
	
	public WebElement getprodByName(String name)
	{
		WebElement reqPoduct= products.stream().filter(s->s.findElement(By.cssSelector("b")).
				getText().equalsIgnoreCase(name)).findFirst().orElse(null);
		return reqPoduct;
	}
	
	public void addToCart(String name)
	{
		WebElement reqProduct = getprodByName(name);
		reqProduct.findElement(addToCartButtonLocator).click();
		waitUntilElementsToAppear(tostmessage);
		waitUntilElementsToDisAppear(animator);
	}
	
	public CartPage goToCart()
	{
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	
	
}
