package reportConfigV5;
import static reportConfigV5.ExtentTestManager.getTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import common.BaseTest;
import reportConfigV5.ExtentManager;

public class ExtentTestListener extends BaseTest implements ITestListener {

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentManager.extentReports.flush();
	}
	
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriver();
		String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		getTest().log(Status.SKIP, "Test Skipped");
	}
}
