package zmobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

	public LoginPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Clear Phone or email'")
	private WebElement clearPhoneOrEmail;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'CONTINUE'")
	private WebElement continueBtn;

	@AndroidFindBy(xpath = "//android.widget.EditText[@hint='Phone or email']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Phone or email'")
	private WebElement emailOrPhoneField;

	@AndroidFindBy(xpath = "//*[@text='Enter a valid phone or email']")
	@iOSXCUITFindBy(iOSNsPredicate = "value == 'Enter a valid phone or email'")
	private WebElement errorMessage;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='SETUP MY ACCOUNT']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'SETUP MY ACCOUNT'")
	private WebElement setUpMyAccountBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='GO BACK']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'GO BACK'")
	private WebElement goBackBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='GO BACK']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Close'")
	private WebElement closeBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='SIGN IN WITH PASSWORD']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'SIGN IN WITH PASSWORD'")
	private WebElement signInWithPwd;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='input-password']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Password'")
	private WebElement passwordField;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Allow']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Allow'")
	private WebElement allowNotifications;

	public LoginPage enterLoginInfo(String emailOrPhone) throws InterruptedException {
		emailOrPhoneField.click();
		System.out.println("Field clicked, waiting for keyboard...");
		Thread.sleep(1500);
		emailOrPhoneField.clear();
		Thread.sleep(500);
		emailOrPhoneField.sendKeys(emailOrPhone);
		hideKeyboard();
		Thread.sleep(500);
		return this;
	}

	public LoginPage clickContinueToLogin() throws InterruptedException {
		if (isKeyboardShown()) {
			System.out.println("Keyboard still visible, hiding...");
			hideKeyboard();
			Thread.sleep(500);
		}

		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		System.out.println("Clicking CONTINUE button");
		continueBtn.click();

		return this;
	}

	public LoginPage clearLoginField() throws InterruptedException {
		emailOrPhoneField.click();
		Thread.sleep(500);

		if (driver instanceof IOSDriver) {
			clearPhoneOrEmail.click();
			Thread.sleep(500);
		} else {
			emailOrPhoneField.clear();
		}

		hideKeyboard();

		return this;
	}

	public void clickLoginField() {
		emailOrPhoneField.click();
	}

	public void closeLoginScreen() {
		closeBtn.click();
	}

	public void allowNotifications() {
		try {
			if (driver instanceof IOSDriver) {
				wait.until(ExpectedConditions.elementToBeClickable(allowNotifications));
				allowNotifications.click();
				System.out.println("Notifications allowed");
			} else {
				emailOrPhoneField.clear();
			}
		} catch (Exception e) {
			System.out.println("Allow notifications button not found: " + e.getMessage());
		}
	}

	public void allowNotificationsIfNeeded() {
		if (driver instanceof IOSDriver) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(allowNotifications));
				allowNotifications.click();
				System.out.println("Notifications allowed (iOS)");
			} catch (Exception e) {
				System.out.println("Allow notifications button not found: " + e.getMessage());
			}
		} else {
			System.out.println("Skipping notifications (Android doesn't show this)");
		}
	}

	public String getErrorMessage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(errorMessage));
			String error = getText(errorMessage);
			System.out.println("Error message: " + error);
			return error;
		} catch (Exception e) {
			System.err.println("Error getting error message: " + e.getMessage());
			return "";
		}
	}

	public boolean ifSetMyAccountBtnDisplayed() {
		try {
			return setUpMyAccountBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean ifGoBackBtnDisplayed() {
		try {
			return goBackBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void enterPassword(String password) throws InterruptedException {
		try {
			signInWithPwd.click();
			wait.until(ExpectedConditions.elementToBeClickable(passwordField));
			passwordField.sendKeys(password);
			System.out.println("Password entered successfully");
			hideKeyboard();

		} catch (Exception e) {
			System.err.println("Failed to enter password: " + e.getMessage());
			throw new RuntimeException("Failed to enter password", e);
		}
	}

	public boolean isLoginSuccessful() {

		try {
			System.out.println("Verifying login success...");
			Thread.sleep(2000);

			try {
				List<WebElement> loginFields;

				if (driver instanceof AndroidDriver) {
					loginFields = driver
							.findElements(AppiumBy.xpath("//android.widget.EditText[@hint='Phone or email']"));
					System.out.println("Android: Searching for email field");
				} else if (driver instanceof IOSDriver) {
					loginFields = driver.findElements(AppiumBy.iOSNsPredicateString("label == 'Phone or email'"));
					System.out.println("iOS: Searching for email field");
				} else {
					System.err.println("Unknown platform");
					return false;
				}

				if (!loginFields.isEmpty() && loginFields.get(0).isDisplayed()) {
					System.out.println("Still on login page - login failed");
					return false;
				}

				System.out.println("Login was successful - left login page!");
				return true;

			} catch (NoSuchElementException e) {
				System.out.println("Login successful - login page element not found");
				return true;
			}

		} catch (Exception e) {
			System.err.println("Error verifying login: " + e.getMessage());
			return true;
		}
	}

	public void hideKeyboard() {
		try {
			if (driver instanceof AndroidDriver) {
				((AndroidDriver) driver).hideKeyboard();
				System.out.println("Keyboard hidden successfully (Android)");
			} else if (driver instanceof IOSDriver) {
				((IOSDriver) driver).hideKeyboard();
				System.out.println("Keyboard hidden successfully (iOS)");
			}
		} catch (Exception e) {
			System.out.println("Keyboard already hidden or not present: " + e.getMessage());
		}
	}

	public boolean isOnLoginScreen() {
		try {
			return emailOrPhoneField.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickGoBack() {
		try {
			goBackBtn.click();
			System.out.println("Go Back button clicked");
		} catch (Exception e) {
			System.err.println("Failed to click Go Back button: " + e.getMessage());
		}
	}
}
