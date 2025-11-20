package zmobile.pages;

import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProfilePage extends BasePage {

	public ProfilePage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "profile_edit")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'editProfile'")
	private WebElement editProfile;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='profile_full_name']")
	@iOSXCUITFindBy(accessibility = "profile_full_name")
	private WebElement profileFullName;

	@AndroidFindBy(accessibility = "profile_edit")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'back right btn'")
	private WebElement backRightBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='profile_logout']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'LOGOUT'")
	private WebElement logout;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete your account and your data']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Delete your account and your data'")
	private WebElement deleteAccount;

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Privacy policy'")
	private WebElement privacyPolicyLink;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All the other stuff']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'All the other stuff'")
	private WebElement allOtherStaffTitle;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ZUMIEZ.COM']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'ZUMIEZ.COM'")
	private WebElement zumiezBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='THE ZUMIEZ STASH FAQS']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'THE ZUMIEZ STASH FAQS'")
	private WebElement stashFaqBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='FIND A STORE']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'FIND A STORE'")
	private WebElement findStoreBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='SEND FEEDBACK']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'SEND FEEDBACK'")
	private WebElement sendFeedbackBtn;

	@AndroidFindBy(accessibility = "more_this_could_be_you")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'THIS COULD BE YOU'")
	private WebElement thisCouldBeYouBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='APPLY FOR A JOB']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'APPLY FOR A JOB'")
	private WebElement applyForJobBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='TERMS OF USE']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'TERMS OF USE'")
	private WebElement termsOfUseBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='PRIVACY POLICY']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'PRIVACY POLICY'")
	private WebElement privacyPolicyBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CALL US']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'CALL US'")
	private WebElement callUsBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PHONE']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'PHONE'")
	private WebElement phone;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='EMAIL']")
	@iOSXCUITFindBy(accessibility = "more_email_button")
	private WebElement email;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CUSTOMER SERVICE HOURS']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'CUSTOMER SERVICE HOURS'")
	private WebElement customerServiceHours;

	public void scrollDownOnProfilePage(String text) {
		scrollToElement(text);
	}

	public boolean allTheOtherStaffTitleDisplayed() {
		if (allOtherStaffTitle.isDisplayed()) {
			System.out.println(GREEN_CHECK + " All the other staff title is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " All the other staff title is not displayed on profile screen");
		return false;
	}

	public boolean zumiezBtnDisplayed() {
		if (zumiezBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " ZUMIEZ.COM button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " ZUMIEZ.COM button  is not displayed on profile screen");
		return false;
	}

	public boolean stashFaqBtnDisplayed() {
		if (stashFaqBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " STASH FAQ button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " STASH FAQ button  is not displayed on profile screen");
		return false;
	}

	public boolean findStoreBtnDisplayed() {
		if (findStoreBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " FIND A STORE button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " FIND A STORE button is not displayed on profile screen");
		return false;
	}

	public boolean sendFeedbackBtnDisplayed() {
		if (sendFeedbackBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " SEND FEEDBACK button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " SEND FEEDBACK button is not displayed on profile screen");
		return false;
	}

	public boolean thisCouldBeYouBtnDisplayed() {
		if (thisCouldBeYouBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " THIS COULD BE YOU button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " THIS COULD BE YOU button is not displayed on profile screen");
		return false;
	}

	public boolean applyForJobBtnDisplayed() {
		if (applyForJobBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " APPLY FOR JOB button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " APPLY FOR JOB button is not displayed on profile screen");
		return false;
	}

	public boolean termsOfUseBtnDisplayed() {
		if (termsOfUseBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " TERMS OF USE button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " TERMS OF USE button is not displayed on profile screen");
		return false;
	}

	public boolean privacyPolicyBtnDisplayed() {
		if (privacyPolicyBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " PRIVACY POLICY button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " PRIVACY POLICY button is not displayed on profile screen");
		return false;
	}

	public boolean callUsBtnDisplayed() {
		if (callUsBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " CALL US button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " CALL US button is not displayed on profile screen");
		return false;
	}

	public boolean phoneDisplayed() {
		if (phone.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Phone is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Phone is not displayed on profile screen");
		return false;
	}

	public boolean emailDisplayed() {
		if (email.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Email is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Email is not displayed on profile screen");
		return false;
	}

	public boolean customerServiceHoursDisplayed() {
		if (customerServiceHours.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Customer service hours are displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Customer service hours are not displayed on profile screen");
		return false;
	}

	public boolean privacyPolicyLinkDisplayed() {
		if (privacyPolicyLink.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Privacy policy link is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Privacy policy link is not displayed on profile screen");
		return false;
	}

	public boolean deleteAccountBtnDisplayed() {
		if (deleteAccount.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Delete account button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Delete account button is not displayed on profile screen");
		return false;
	}

	public boolean logoutBtnDisplayed() {
		if (logout.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Logout button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Logout button is not displayed on profile screen");
		return false;
	}

	public boolean profileFullNameDisplayed() {
		if (profileFullName.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Profile full name is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Profile full name is not displayed on profile screen");
		return false;
	}

	public void editProfile() {
		editProfile.click();
	}

	public boolean editProfileIconDisplayed() {
		if (editProfile.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Edit profile icon is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Edit profile icon is not displayed on profile screen");
		return false;
	}

	public boolean backRightBtnDisplayed() {
		if (backRightBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Back right button is displayed on profile screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Back right button is not displayed on profile screen");
		return false;
	}

	public void selectLogoutBtn() {
		try {
			System.out.println("Finding and clicking logout button");
			scrollToElement("LOGOUT");
			Thread.sleep(500);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement logoutButton = null;

			try {
				if (driver instanceof AndroidDriver) {
					System.out.println("Android: Finding logout button");
					logoutButton = wait.until(ExpectedConditions
							.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@text='LOGOUT']")));
				} else if (driver instanceof IOSDriver) {
					System.out.println("iOS: Finding logout button");
					logoutButton = wait.until(ExpectedConditions
							.elementToBeClickable(AppiumBy.iOSNsPredicateString("label == 'LOGOUT'")));
				}

				if (logoutButton != null) {
					System.out.println("Logout button found and clickable, clicking now...");
					logoutButton.click();
					System.out.println("Logout button clicked successfully");
				} else {
					throw new RuntimeException("Logout button not found on this platform");
				}

			} catch (TimeoutException e) {
				System.err.println("Timeout waiting for logout button: " + e.getMessage());
				throw new RuntimeException("Logout button not found within timeout", e);
			}

		} catch (Exception e) {
			System.err.println("Error clicking logout button: " + e.getMessage());
			throw new RuntimeException("Failed to click logout button: " + e.getMessage(), e);
		}
	}

	public void scrollToElementAndroid(String text) {
		try {
			String uiScrollableCommand = "new UiScrollable(new UiSelector()" + ".scrollable(true))"
					+ ".setAsVerticalList()" + ".scrollIntoView(new UiSelector()" + ".text(\"" + text + "\"))";

			driver.findElement(AppiumBy.androidUIAutomator(uiScrollableCommand));
			System.out.println("✓ Found and scrolled to text: " + text);
		} catch (Exception e) {
			System.out.println("✗ Failed to scroll to text: " + text + "\nError: " + e.getMessage());
		}
	}

	private void scrollToElementIOS(String elementLabel) throws InterruptedException {
		System.out.println("Using iOS mobile scroll");

		boolean elementFound = false;
		int maxScrollAttempts = 20;
		int scrollAttempt = 0;

		while (!elementFound && scrollAttempt < maxScrollAttempts) {
			try {
				List<WebElement> elements = driver.findElements(MobileBy
						.iOSNsPredicateString("label == '" + elementLabel + "' OR value == '" + elementLabel + "'"));

				if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
					System.out.println(GREEN_CHECK + " Element '" + elementLabel + "' found after " + scrollAttempt
							+ " scroll(s)");
					elementFound = true;
					break;
				}

				System.out.println("Scrolling down (attempt " + (scrollAttempt + 1) + "/" + maxScrollAttempts + ")");
				((JavascriptExecutor) driver).executeScript("mobile: scroll",
						ImmutableMap.of("direction", "down", "momentum", -5));

				scrollAttempt++;
				Thread.sleep(300);

			} catch (Exception e) {
				System.err.println(RED_X + " Error during scroll attempt " + scrollAttempt + ": " + e.getMessage());
			}
		}

		if (!elementFound) {
			throw new RuntimeException(RED_X + " Element '" + elementLabel + "' not found after " + maxScrollAttempts
					+ " scroll attempts");
		}
	}

	public void findElementByText(String elementText) {
		String platformName = driver.getCapabilities().getPlatformName().toString();

		System.out.println("Platform: " + platformName);
		System.out.println("Scrolling to element with text: " + elementText);

		try {
			if ("ANDROID".equalsIgnoreCase(platformName)) {
				scrollToElementAndroid(elementText);
			} else if ("IOS".equalsIgnoreCase(platformName)) {
				scrollToElementIOS(elementText);

			} else {
				throw new RuntimeException("Unsupported platform: " + platformName);
			}
		} catch (Exception e) {
			System.err.println("Error scrolling to element: " + e.getMessage());
			throw new RuntimeException("Failed to scroll to element: " + elementText, e);
		}
	}
}
