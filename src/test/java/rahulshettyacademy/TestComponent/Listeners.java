package rahulshettyacademy.TestComponent;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> th = new ThreadLocal();
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		th.set(test);//this method is used to allocate a separate thread id to each test case.
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		//th.get()-->this method is used to get the id of test cases. 
		th.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		th.get().log(Status.FAIL, "Test failed");
		th.get().fail(result.getThrowable());
		
		//To take the screenshot
		
		try
		{
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String path=null;
		try {
			path = getScreenShot(result.getMethod().getMethodName(),driver);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		// attaching screen shot to the report
		th.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
