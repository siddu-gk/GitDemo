package rahulshettyacademy.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.LandingPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	CartPage cartPage;
	
	//Given I Landed on Ecommerce page
	@Given("I Landed on Ecommerce page")
	public void I_Landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchDriver();
	}
	
	//Given Logged in with userName <username> and passWord <password>
	@Given("^Logged in with userName (.+) and passWord (.+)$")
	public void Logged_in_with_userName_and_passWord(String username,String password) throws InterruptedException
	{
		productCatalogue = landingPage.loginApplication(username,password);
		List<WebElement> prodList = productCatalogue.getProdList();
	}
	
	//When I add productName <productname> to cart
	
	@When("^I add productName (.+) to cart$")
	public void I_add_productName_to_cart(String productname) throws InterruptedException
	{
		productCatalogue.addToCart(productname);
		Thread.sleep(3000);
	}
	
	//And got to cartPage
	@And("go to cartPage")
	public void got_to_cartPage() throws InterruptedException
	{
		cartPage = productCatalogue.goToCart();
	}
	
	//Then I verify the product <productname> from cartPage
	@Then("^I verify the product (.+) from cartPage$")
	public void I_verify_the_product_from_cartPage(String productname) throws InterruptedException
	{
		Thread.sleep(3000);
		Boolean res = cartPage.varifyCartProduct(productname);		
		Assert.assertTrue(res);
	}
	
	//Then "Incorrect emai or password." is displayed on login page
	@Then("{string} is displayed on login page")
	public void Error_message_is_Displayed(String arg1)
	{
		Assert.assertEquals(arg1, landingPage.getErrorMessage());
	}
	
}
