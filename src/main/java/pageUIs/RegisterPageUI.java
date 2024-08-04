package pageUIs;

public class RegisterPageUI {
	public static final String FIRSTNAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LASTNAME_TEXTBOX = "//input[@id='LastName']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//button[@id='register-button']";
	
	public static final String FIRSTNAME_ERROR_MESSAGE = "//span[@id='FirstName-error']";
	public static final String LASTNAME_ERROR_MESSAGE = "//span[@id='LastName-error']";
	public static final String EMAIL_ERROR_MESSAGE = "//span[@id='Email-error']";
	public static final String PASSWORD_ERROR_MESSAGE = "//span[@id='ConfirmPassword-error']";
	
	public static final String VALID_INFORMATION_MESSAGE ="//div[@class='result']";
	public static final String DATE_DROPDOWN ="//select[@name='DateOfBirthDay']";
	public static final String MONTH_DROPDOWN ="//select[@name='DateOfBirthMonth']";
	public static final String YEAR_DROPDOWN ="//select[@name='DateOfBirthYear']";
	public static final String EMAIL_EXIST_MESSAGE ="//div[@class='message-error validation-summary-errors']";
	public static final String LOGOUT_LINK ="//a[@class='ico-logout']";
	public static final String PASSWORD_LEAST_6_CHARACTERS_MESSAGE="//span[@class='field-validation-error']";
	public static final String CONFIRMATION_PASSWORD_MESSAGE = "//span[@id='ConfirmPassword-error']" ;
}
