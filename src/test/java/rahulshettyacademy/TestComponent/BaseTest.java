package rahulshettyacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import rahulshettyacademy.pageobject.LandingPage;

public class BaseTest {

	public WebDriver driver;
	 public LandingPage landingPage;
	 
	 public WebDriver initializeDriver() throws IOException
	 {
		 Properties prop=new Properties();
		 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		 prop.load(fis);
		 
		 String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		 if(browserName.equalsIgnoreCase("chrome"))
		 {
			 driver=new ChromeDriver();
		 }
		 else if(browserName.equalsIgnoreCase("edge"))
		 {
			 System.setProperty("webdriver.edge.driver","C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
			 driver=new EdgeDriver();
		 }
		 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 return driver;
	 }
	 
	 public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	 {
		 TakesScreenshot ts = (TakesScreenshot)driver;
		 System.out.println(ts);
		 File src = ts.getScreenshotAs(OutputType.FILE);
		 System.out.println(src);
		 //creating new file
		 File path = new File(System.getProperty("user.dir")+"//Reports"+testCaseName+".png");
		 FileUtils.copyFile(src, path);
		 return System.getProperty("user.dir")+"//Reports"+testCaseName+".png";
		 
	 }
	
	 @BeforeMethod
	 public LandingPage launchDriver() throws IOException
	 {
		 driver = initializeDriver();
		 landingPage = new LandingPage(driver);
		 landingPage.goTo();
		 return landingPage;
	 }
	
	 @AfterMethod
	 public void teardown()
	 {
		 driver.close();
	 }
}