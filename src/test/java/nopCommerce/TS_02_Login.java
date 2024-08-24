package nopCommerce;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import common.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import utils.ExcelUtil;

public class TS_02_Login extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		// start browser lên
		driver = getBrowserDriver(browserName);
		loginPage = new LoginPageObject(driver);
		loginPage.openUrl(driver, "http://localhost:8010/login");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		loginPage.clickToLoginButton();
		Assert.assertTrue(loginPage.isEmailErrorMessage("Please enter your email"));
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
//		loginPage.refreshCurrentPage(driver);
		loginPage.clickLoginLink();
		loginPage.enterToEmailTextbox("abcd");
		loginPage.clickToLoginButton();
		Assert.assertTrue(loginPage.isEmailErrorMessage("Please enter a valid email address."));
	}

	@Test
	public void TC_03_LoginWithUnregisteredEmail() {
//		loginPage.refreshCurrentPage(driver);
		loginPage.clickLoginLink();
		loginPage.enterToEmailTextbox("abcpq@mail.com");
		loginPage.clickToLoginButton();
		Assert.assertTrue(
				loginPage.isEmail_PassMessage("Login was unsuccessful. Please correct the errors and try again."));
		Assert.assertTrue(loginPage.isEmail_PassMessage("No customer account found"));
	}

	@Test
	public void TC_04_LoginWithPasswordBlank() {
//		loginPage.refreshCurrentPage(driver);
		loginPage.clickLoginLink();
		loginPage.enterToEmailTextbox(GlobalConstants.username);
		loginPage.enterToPasswordTextbox("");
		loginPage.clickToLoginButton();
		Assert.assertTrue(
				loginPage.isEmail_PassMessage("Login was unsuccessful. Please correct the errors and try again."));
		Assert.assertTrue(loginPage.isEmail_PassMessage("The credentials provided are incorrect"));
	}

	@Test
	public void TC_05_LoginWithPasswordWrong() {
//		loginPage.refreshCurrentPage(driver);
		loginPage.clickLoginLink();
		loginPage.enterToEmailTextbox(GlobalConstants.username);
		loginPage.enterToPasswordTextbox("654321");
		loginPage.clickToLoginButton();
		Assert.assertTrue(
				loginPage.isEmail_PassMessage("Login was unsuccessful. Please correct the errors and try again."));
		Assert.assertTrue(loginPage.isEmail_PassMessage("The credentials provided are incorrect"));
	}

	@Test
	public void TC_06_LoginSuccessfulHomePage() {
//		loginPage.refreshCurrentPage(driver);
		loginPage.clickLoginLink();
		loginPage.enterToEmailTextbox(GlobalConstants.username);
		loginPage.enterToPasswordTextbox(GlobalConstants.password);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountPresent("My account"));
	}

	@Test
	public void TC_07_LoginWithDataDriven() throws IOException {
		ExcelUtil.setExcelFile("Auto");

		// Không dùng vòng lặp, chỉ duyệt record đầu tiên trong Sheet
		String excelUserName = ExcelUtil.getCellData(1, 1);
		String excelPassword = ExcelUtil.getCellData(1, 2);
		
		loginPage.clickLoginLink();
		loginPage.enterToEmailTextbox(excelUserName);
		loginPage.enterToPasswordTextbox(excelPassword);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		String homePageUrl = homePage.getPageUrl(driver);
		if(homePageUrl.equals("http://localhost:8010/")) {
			ExcelUtil.setCellData("Pass", 1, 3);
		} else {
			ExcelUtil.setCellData("Fail", 1, 3);
		}
	}

}
