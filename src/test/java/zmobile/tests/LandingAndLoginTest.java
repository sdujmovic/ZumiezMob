package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import zmobile.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class LandingAndLoginTest extends MobileBaseTest {

	@Test(priority = 3)
	@ZephyrTest(key = "HTH-T143", description = "Login with empty fields validation")
	public void testLoginWithEmptyFields() throws InterruptedException {
		System.out.println("Starting test: Login with empty fields");
		String expectedError = TestDataReader.getErrorMessage("emptyFields");
		Thread.sleep(500);

		loginPage.clickContinueToLogin();
		Thread.sleep(500);

		String errorMsg = loginPage.getErrorMessage();
		Assert.assertTrue(errorMsg.contains(expectedError), "Error message should be displayed for empty fields");

		try {
			loginPage.closeLoginScreen();
		} catch (Exception e) {
			System.out.println("Could not close login screen: " + e.getMessage());
			loginPage.hideKeyboard();
		}

		System.out.println("Test passed: Empty fields validation works");
	}

	@Test(priority = 2)
	@ZephyrTest(key = "HTH-T143", description = "Login with multiple invalid inputs")
	public void testLoginWithMultipleInvalidInputs() throws Exception {
		System.out.println("Starting test: Login with multiple invalid inputs");

		String[] invalidTypes = { "invalidEmail", "invalidFormat", "tooShort", "numbersOnly", "specialCharsOnly" };
		String expectedError = TestDataReader.getErrorMessage("invalidInput");

		for (String type : invalidTypes) {
			String invalidInput = TestDataReader.getInvalidCredential(type);
			System.out.println("Testing with: " + invalidInput);

			loginPage.enterLoginInfo(invalidInput);
			Thread.sleep(500);

			loginPage.clickContinueToLogin();
			Thread.sleep(500);

			String errorMsg = loginPage.getErrorMessage();
			Assert.assertTrue(errorMsg.contains(expectedError),
					"Error message should be displayed for: " + invalidInput);

			loginPage.clickLoginField();

			try {
				loginPage.clearLoginField();
			} catch (Exception e) {
				System.out.println("Could not clear field: " + e.getMessage());
				loginPage.hideKeyboard();
			}

			Thread.sleep(500);
		}

		System.out.println("Test passed: All invalid inputs validated");
	}

	@Test(priority = 1)
	@ZephyrTest(key = "HTH-T143", description = "Login with not registered user")
	public void testLoginWithNotRegisteredUser() throws Exception {
		System.out.println("Starting test: Login with not registered user");

		String notRegisteredUser = TestDataReader.getNotRegisteredUser("email");
		System.out.println("Testing with: " + notRegisteredUser);

		loginPage.enterLoginInfo(notRegisteredUser);
		Thread.sleep(500);
		loginPage.hideKeyboard();
		loginPage.clickContinueToLogin();
		Thread.sleep(500);

		Assert.assertTrue(loginPage.ifSetMyAccountBtnDisplayed(), "SET MY ACCOUNT button should be displayed");
		Assert.assertTrue(loginPage.ifGoBackBtnDisplayed(), "GO BACK button should be displayed");

		loginPage.clickGoBack();
		loginPage.hideKeyboard();
		Thread.sleep(1000);

		loginPage.clickLoginField();

		try {
			loginPage.clearLoginField();
		} catch (Exception e) {
			System.out.println("Could not clear field (may have navigated away): " + e.getMessage());
			loginPage.hideKeyboard();
		}

		loginPage.hideKeyboard();
		System.out.println(
				"Test passed: Option Set Up My Account and Go Back buttons appear when trying to login with not registered user");
	}

	@Test(priority = 4)
	@ZephyrTest(key = "HTH-T143", description = "Login with valid existing user by email")
	public void testLoginWithValidExistingUser() throws Exception {

		String email = TestDataReader.getValidEmail();
		String password = TestDataReader.getValidPassword();

		System.out.println("Starting test: Login with valid existing user by email");
		System.out.println("Build type: " + TestDataReader.getCurrentBuildType());
		System.out.println("Testing with email: " + email);

		loginPage.enterLoginInfo(email);
		Thread.sleep(500);

		loginPage.clickContinueToLogin();
		Thread.sleep(1000);

		loginPage.enterPassword(password);
		Thread.sleep(500);

		loginPage.clickContinueToLogin();
		Thread.sleep(2000);
		loginPage.allowNotificationsIfNeeded();

		Assert.assertTrue(loginPage.isLoginSuccessful(), "User should be logged in successfully");

		header.selectProfileIcon();
		profilePage.selectLogoutBtn();

		Thread.sleep(5000);
		Thread.sleep(2000);
		System.out.println(
				"Test passed: Login successful with email " + TestDataReader.getCurrentBuildType() + " credentials");
	}
}
