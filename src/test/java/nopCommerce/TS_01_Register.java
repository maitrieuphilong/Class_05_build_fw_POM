package nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import common.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
import reportConfigV5.ExtentTestManager;
import utils.DataHelper;

public class TS_01_Register extends BaseTest {
	WebDriver driver;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	DataHelper data;
	String firstName;
	String lastName;
	String emailRandom;
	String password;

	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		// start browser lên
		driver = getBrowserDriver(browserName);

		// khởi tạo trang Register
		registerPage = new RegisterPageObject(driver);
		registerPage.openUrl(driver, "http://localhost:8010/register");

		data = DataHelper.getData();
		firstName = data.getFirstName();
		lastName = data.getLastName();
		emailRandom = data.getEmail();
		password = "111111";
		
		System.out.println("first name using:" + firstName);
		System.out.println("last name using:" + lastName);
		System.out.println("email using:" + emailRandom);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	@Test
	public void TC_01_RegisterWithEmptyData() {
		extentLog("Step 1: click register button");
		registerPage.clickToRegisterButton();
		extentLog("Step 2: verify error message show up");
		Assert.assertTrue(registerPage.isFirstnameErrorMessage("First name is required."));
		Assert.assertTrue(registerPage.isLastnameErrorMessage("Last name is required."));
		Assert.assertTrue(registerPage.isEmailErrorMessage("Email is required."));
		Assert.assertTrue(registerPage.isPasswordErrorMessage("Password is required."));
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		extentLog("Step 1: renew page");
		registerPage.clickRegisterLink();
		extentLog("Step 2: enter invalid email");
		registerPage.enterToEmailTextbox("abcxyz");
		extentLog("Step 3: click register button");
		registerPage.clickToRegisterButton();
		extentLog("Step 4: verify error invalid email");
		Assert.assertTrue(registerPage.isEmailErrorMessage("Please enter a valid email address."));
	}

//	@Test
	public void TC_03_RegisterWithValidInformation() {
		registerPage.clickRegisterLink();
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailRandom);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isValidInformationMessage("Your registration completed"));
		registerPage.clickLogoutLink();
		homePage = new HomePageObject(driver);
		homePage.clickRegisterLink();
	}

//	@Test
	public void TC_04_RegisterWithExistEmail() {
		registerPage.clickRegisterLink();
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailRandom);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isEmailExistMessage("The specified email already exists"));
	}

	@Test
	public void TC_05_RegisterWithPasswordLeast6Characters() {
		extentLog("Step 1: renew page");
		registerPage.clickRegisterLink();
		extentLog("Step 2: enter firstName: " + firstName);
		registerPage.enterToFirstNameTextbox(firstName);
		extentLog("Step 3: enter lastName: " + lastName);
		registerPage.enterToLastNameTextbox(lastName);
		extentLog("Step 4: enter email: " + emailRandom);
		registerPage.enterToEmailTextbox(emailRandom);
		extentLog("Step 5: enter short password");
		registerPage.enterToPasswordTextbox("12345");
		registerPage.enterToConfirmPasswordTextbox("12345");
		extentLog("Step 6: click register button");
		registerPage.clickToRegisterButton();
		extentLog("Step 7: verify password must have at least 6 characters");
		Assert.assertTrue(registerPage.isPasswordLeast6Characters("Password must meet the following rules:"));
		Assert.assertTrue(registerPage.isPasswordLeast6Characters("must have at least 6"));
	}

//	@Test
	public void TC_06_RegisterWithConfirmPassword() {
		registerPage.clickRegisterLink();
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailRandom);
		// password mismatch with confirm password
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("654321");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isPasswordErrorMessage("The password and confirmation password do not match"));
	}

}
