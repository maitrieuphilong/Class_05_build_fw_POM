package common;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;

import reportConfigV5.ExtentTestManager;

public class BaseTest {
	public WebDriver driver;
	
	public WebDriver getBrowserDriver(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if(browserName.equalsIgnoreCase("chromeIncog")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		} else {
			throw new RuntimeException("Please enter correct browser name");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.implicit_timeout));
		return driver;
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeMethod
	protected void extentStartTest(Method method) {
		ExtentTestManager.startTest(method.getName(), method.getName());
	}
	
	protected void extentLog(String log) {
		ExtentTestManager.getTest().log(Status.INFO, log);
	}
	
	
}
