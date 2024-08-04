package nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.GlobalConstants;
import pageObjects.RegisterPageObject;

public class TS_00_Precondition {
	WebDriver driver;
	RegisterPageObject registerPage;
	
	@BeforeClass
	public void beforeClass() {
		// start browser lên
		driver = new FirefoxDriver();
		
		// khởi tạo trang Register
		registerPage = new RegisterPageObject(driver);
		registerPage.openUrl(driver, "https://demo.nopcommerce.com/register?returnUrl=%2Fwishlist");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	@Test
	public void TC_01_CreateAccountForTesting() {
		// Điền thông tin hợp lệ để tạo account test, Bấm button Register
		registerPage.enterToFirstNameTextbox("auto");
		registerPage.enterToLastNameTextbox("test");
		registerPage.enterToEmailTextbox(GlobalConstants.username);
		registerPage.enterToPasswordTextbox(GlobalConstants.password);
		registerPage.enterToConfirmPasswordTextbox(GlobalConstants.password);
		registerPage.clickToRegisterButton();
		
		// Verify account được tạo thành công. Nhấn Logout account
		Assert.assertTrue(registerPage.isValidInformationMessage("Your registration completed"));
		registerPage.clickLogoutLink();
		
	}
}
