package rahulshettyacademy.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.testng.Assert;
import rahulshettyacademy.Data.DataReader;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckOutPage;
import rahulshettyacademy.pageobject.LandingPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	DataReader d = new DataReader();

	@Test(dataProvider="getData")
	public void main (HashMap<String,String> h) throws InterruptedException
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication(h.get("email"),h.get("Password"));
		Thread.sleep(3000);
		List<WebElement> prodList = productCatalogue.getProdList();
		Thread.sleep(3000);
		productCatalogue.addToCart(h.get("name"));
		CartPage cartPage = productCatalogue.goToCart();
		Thread.sleep(3000);
		Boolean res = cartPage.varifyCartProduct(h.get("name"));
		Thread.sleep(3000);
		Assert.assertTrue(res);
//		Thread.sleep(3000);
//		CheckOutPage checkOutPage = cartPage.checkOut();
//		checkOutPage.selectCountry(h.get("country"));
//		Thread.sleep(5000);
//		checkOutPage.placeOrder();
//		Thread.sleep(5000);
//		String mssg = checkOutPage.confirmationPageMessage();
//		Assert.assertTrue(mssg.equalsIgnoreCase("Thankyou for the order."));
//		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = d.getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
