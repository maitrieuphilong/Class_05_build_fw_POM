package pageObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	// contructor
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}
	
	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}
	
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	
	public boolean isValidInformationMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.VALID_INFORMATION_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isFirstnameErrorMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isLastnameErrorMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isEmailErrorMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isPasswordErrorMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return message.contains(value);
	}
	
	public void clickLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
	}
	
	public void clickRegisterLink() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_LINK);
		clickToElement(driver, RegisterPageUI.REGISTER_LINK);
	}
	
	public boolean isEmailExistMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.EMAIL_EXIST_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isPasswordLeast6Characters(String value) {
		moveToElement(driver, RegisterPageUI.PASSWORD_LEAST_6_CHARACTERS_MESSAGE);
		String message = getTextOfElement(driver, RegisterPageUI.PASSWORD_LEAST_6_CHARACTERS_MESSAGE);
		return message.contains(value);
	}
	
}
