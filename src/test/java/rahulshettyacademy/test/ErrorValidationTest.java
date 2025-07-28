package rahulshettyacademy.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import org.testng.Assert;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class ErrorValidationTest extends BaseTest{

	//loginErrorValidation->this test will fail due to miss match in the expected condition.
	@Test
	public void loginErrorValidation() throws InterruptedException, IOException
	{
		String name="ZARA COAT 3";
		landingPage.loginApplication("siddugk13@gmail.com","Satish1998");
		Assert.assertEquals("Incorrect emai or password.", landingPage.getErrorMessage());	
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException, IOException
	{
		String name="ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("siddugk123@gmail.com","Satish1998");
		List<WebElement> prodList = productCatalogue.getProdList();
		Thread.sleep(3000);
		productCatalogue.addToCart(name);
		CartPage cartPage = productCatalogue.goToCart();
		Thread.sleep(3000);
		Boolean res = cartPage.varifyCartProduct(name);
		Thread.sleep(3000);
		Assert.assertTrue(res);
	}
	
}
