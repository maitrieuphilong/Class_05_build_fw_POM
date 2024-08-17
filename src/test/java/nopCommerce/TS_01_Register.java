package nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class TS_01_Register {
	WebDriver driver;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	
	@BeforeClass
	public void beforeClass() {
		// start browser lên
		driver = new FirefoxDriver();
		
		// khởi tạo trang Register
		registerPage = new RegisterPageObject(driver);
		registerPage.openUrl(driver, "http://localhost:8010/register");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	@Test
	public void TC_01_RegisterWithEmptyData() {
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isFirstnameErrorMessage("First name is required."));
		Assert.assertTrue(registerPage.isLastnameErrorMessage("Last name is required."));
		Assert.assertTrue(registerPage.isEmailErrorMessage("Email is required."));
		Assert.assertTrue(registerPage.isPasswordErrorMessage("Password is required."));
	}
	
	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		registerPage.clickRegisterLink();
		registerPage.enterToEmailTextbox("abcxyz");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isEmailErrorMessage("Please enter a valid email address."));
	}
	
	@Test
	public void TC_03_RegisterWithValidInformation() {
		registerPage.clickRegisterLink();
		registerPage.enterToFirstNameTextbox("qa");
		registerPage.enterToLastNameTextbox("test");
		registerPage.enterToEmailTextbox("abc6@gmail.com");
		registerPage.enterToPasswordTextbox("111111");
		registerPage.enterToConfirmPasswordTextbox("111111");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isValidInformationMessage("Your registration completed"));
		registerPage.clickLogoutLink();
		homePage = new HomePageObject(driver);
		homePage.clickRegisterLink();
	}
	
	@Test
	public void TC_04_RegisterWithExistEmail() {
		registerPage.clickRegisterLink();
		registerPage.enterToFirstNameTextbox("qa2");
		registerPage.enterToLastNameTextbox("test2");
		registerPage.enterToEmailTextbox("abc@gmail.com");
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");

		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isEmailExistMessage("The specified email already exists"));
	}
	
	@Test
	public void TC_05_RegisterWithPasswordLeast6Characters() {
		registerPage.clickRegisterLink();
		registerPage.enterToFirstNameTextbox("abc");
		registerPage.enterToLastNameTextbox("xyz");
		registerPage.enterToEmailTextbox("xyz@gmail.com");
		registerPage.enterToPasswordTextbox("12345");
		registerPage.enterToConfirmPasswordTextbox("12345");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isPasswordLeast6Characters("Password must meet the following rules:"));
		Assert.assertTrue(registerPage.isPasswordLeast6Characters("must have at least 6"));
	}
	
	@Test
	public void TC_06_RegisterWithConfirmPassword() {
		registerPage.clickRegisterLink();
		registerPage.enterToFirstNameTextbox("abc");
		registerPage.enterToLastNameTextbox("xyz");
		registerPage.enterToEmailTextbox("xyz@gmail.com");
		// password mismatch with confirm password
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("654321");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(
				registerPage.isPasswordErrorMessage("The password and confirmation password do not match"));
	}
	
}
