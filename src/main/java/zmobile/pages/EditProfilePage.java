package zmobile.pages;

import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import zmobile.utils.TestDataReader;

public class EditProfilePage extends BasePage {

	public EditProfilePage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Account Information']")
	@iOSXCUITFindBy(accessibility = "profile_edit_account_header")
	private WebElement accountInformationTitle;

	@AndroidFindBy(accessibility = "profile_edit_first_name")
	@iOSXCUITFindBy(accessibility = "profile_edit_first_name")
	private WebElement firstName;

	@AndroidFindBy(accessibility = "profile_edit_last_name")
	@iOSXCUITFindBy(accessibility = "profile_edit_last_name")
	private WebElement lastName;

	@AndroidFindBy(accessibility = "profile_edit_email")
	@iOSXCUITFindBy(accessibility = "profile_edit_email")
	private WebElement email;

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='profile_edit_phone']")
	@iOSXCUITFindBy(accessibility = "profile_edit_phone")
	private WebElement phone;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.zumiez.sandbox:id/edit_profile_birthday']")
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.zumiez:id/edit_profile_birthday']")
	@iOSXCUITFindBy(accessibility = "profile_edit_birthday")
	private WebElement birthday;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.zumiez.sandbox:id/genderInput']")
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.zumiez:id/genderInput']")
	@iOSXCUITFindBy(accessibility = "profile_edit_gender")
	private WebElement gender;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.zumiez.sandbox:id/ethnicityInput']")
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.zumiez:id/ethnicityInput']")
	@iOSXCUITFindBy(accessibility = "profile_edit_ethnicity")
	private WebElement ethnicity;

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='profile_edit_member_since']")
	@iOSXCUITFindBy(accessibility = "profile_edit_member_since")
	private WebElement memberSince;

	@AndroidFindBy(accessibility = "profile_edit_shipping_header")
	@iOSXCUITFindBy(accessibility = "profile_edit_shipping_header")
	private WebElement homeAddressTitle;

	@AndroidFindBy(accessibility = "profile_edit_shipping_address")
	@iOSXCUITFindBy(accessibility = "profile_edit_shipping_address")
	private WebElement address;

	@AndroidFindBy(accessibility = "profile_edit_shipping_city")
	@iOSXCUITFindBy(accessibility = "profile_edit_shipping_city")
	private WebElement city;

	@AndroidFindBy(accessibility = "profile_edit_shipping_state")
	@iOSXCUITFindBy(accessibility = "profile_edit_shipping_state")
	private WebElement state;

	@AndroidFindBy(accessibility = "profile_edit_shipping_zipcode")
	@iOSXCUITFindBy(accessibility = "profile_edit_shipping_zipcode")
	private WebElement zipCode;

	@AndroidFindBy(accessibility = "profile_edit_shipping_country")
	@iOSXCUITFindBy(accessibility = "profile_edit_shipping_country")
	private WebElement country;

	@AndroidFindBy(accessibility = "profile_edit_shipping_phone")
	@iOSXCUITFindBy(accessibility = "profile_edit_shipping_phone")
	private WebElement shippingPhone;

	@AndroidFindBy(accessibility = "profile_edit_employee_header")
	@iOSXCUITFindBy(accessibility = "profile_edit_employee_header")
	private WebElement zumiezEmployeeTitle;

	@AndroidFindBy(accessibility = "profile_edit_employee_sales_code")
	@iOSXCUITFindBy(accessibility = "profile_edit_employee_sales_code")
	private WebElement employeeSalesCode;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='SAVE CHANGES']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'SAVE CHANGES'")
	private WebElement saveChangesBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='UPDATE']")
	private WebElement updateBtn;

	public String validateAccountInformationTitle() {
		try {
			String titleText = accountInformationTitle.getText();
			if (titleText != null && !titleText.isEmpty()) {
				System.out.println(GREEN_CHECK + " TEST PASSED: Title is displayed as expected: " + titleText);
			} else {
				System.out.println(RED_X + " TEST FAILED: Account Information title is not displayed");
			}
			return titleText;
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get Account Information title ");
			return null;
		}
	}

	public String validateHomeAddressTitle() {
		try {
			String titleText = homeAddressTitle.getText();
			if (titleText != null && !titleText.isEmpty()) {
				System.out.println(GREEN_CHECK + " TEST PASSED: Title is displayed: " + titleText);
			} else {
				System.out.println(RED_X + " TEST FAILED: Home Address title is not displayed");
			}
			return titleText;
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get Home Address title ");
			return null;
		}
	}

	public String validateZumiezEmployeeTitle() {
		try {
			String titleText = zumiezEmployeeTitle.getText();
			if (titleText != null && !titleText.isEmpty()) {
				System.out.println(GREEN_CHECK + " TEST PASSED: Title is displayed as expected: " + titleText);
			} else {
				System.out.println(RED_X + " TEST FAILED: Zumiez Employee title is not displayed");
			}
			return titleText;
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get Zumiez Employee title ");
			return null;
		}
	}

	public boolean firstNameDisplayed() {
		if (firstName.isDisplayed()) {
			System.out.println(GREEN_CHECK + " First name is displayed on edit profile screen as expected: "
					+ firstName.getText());
			return true;
		} else
			System.out.println(RED_X + " First name is not displayed on edit profile screen");
		return false;
	}

	public boolean lastNameDisplayed() {
		if (lastName.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + " Last name is displayed on edit profile screen as expected: " + lastName.getText());
			return true;
		} else
			System.out.println(RED_X + " Last name is not displayed on edit profile screen");
		return false;
	}

	public boolean emailDisplayed() {
		if (email.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + " Email is displayed on edit profile screen as expected: " + email.getText());
			return true;
		} else
			System.out.println(RED_X + " Email is not displayed on edit profile screen");
		return false;
	}

	public boolean phoneDisplayed() {
		if (phone.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + " Phone is displayed on edit profile screen as expected: " + phone.getText());
			return true;
		} else
			System.out.println(RED_X + " Phone is not displayed on edit profile screen");
		return false;
	}

	public boolean memberSinceDisplayed() {
		if (memberSince.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Member since is displayed on edit profile screen as expected: "
					+ memberSince.getText());
			return true;
		} else
			System.out.println(RED_X + " Member since is not displayed on edit profile screen");
		return false;
	}

	public boolean birthdayDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == 'Birthday (MM/DD/YYYY)'")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " Birthday is displayed");
					return true;
				} else {
					System.out.println(RED_X + " Birthday is not found");
					return false;
				}

			} else {
				String id = getBirthdayResourceId();
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id(id)));
				System.out.println(GREEN_CHECK + " Birthday is displayed " + element.getText());
				return element.isEnabled();
			}
		} catch (Exception e) {
			System.out.println(RED_X + " Birthday is not found " + e.getMessage());
			return false;
		}
	}

	private String getBirthdayResourceId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/edit_profile_birthday";
		case "prod":
		case "production":
			return "com.zumiez:id/edit_profile_birthday";
		default:
			return "com.zumiez.sandbox:id/edit_profile_birthday";
		}
	}

	public boolean genderDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == 'Ethnicity'")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " Ethnicity is displayed");
					return true;
				} else {
					System.out.println(RED_X + " Ethnicity is not found");
					return false;
				}

			} else {
				String id = getEthnicityResourceId();
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id(id)));
				System.out.println(GREEN_CHECK + " Ethnicity is displayed " + element.getText());
				return element.isEnabled();
			}
		} catch (Exception e) {
			System.out.println(RED_X + " Ethnicity is not found " + e.getMessage());
			return false;
		}
	}

	private String getEthnicityResourceId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/ethnicityInput";
		case "prod":
		case "production":
			return "com.zumiez:id/ethnicityInput";
		default:
			return "com.zumiez.sandbox:id/ethnicityInput";
		}
	}

	public boolean ethnicityDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == 'Gender'")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " Gender is displayed");
					return true;
				} else {
					System.out.println(RED_X + " Gender is not found");
					return false;
				}

			} else {
				String id = getGenderResourceId();
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id(id)));
				System.out.println(GREEN_CHECK + " Gender is displayed " + element.getText());
				return element.isEnabled();
			}
		} catch (Exception e) {
			System.out.println("✗ Gender is not found " + e.getMessage());
			return false;
		}
	}

	private String getGenderResourceId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/genderInput";
		case "prod":
		case "production":
			return "com.zumiez:id/genderInput";
		default:
			return "com.zumiez.sandbox:id/genderInput";
		}
	}

	public void scrollToEditProfileScreenBottom() {
		scrollToElement("Zumiez Employee");
	}

	public boolean addressDisplayed() {
		if (address.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + " Address is displayed on edit profile screen as expected: " + address.getText());
			return true;
		} else
			System.out.println(RED_X + " Address is not displayed on edit profile screen");
		return false;
	}

	public boolean cityDisplayed() {
		if (city.isDisplayed()) {
			System.out
					.println(GREEN_CHECK + " City is displayed on edit profile screen as expected: " + city.getText());
			return true;
		} else
			System.out.println(RED_X + " City is not displayed on edit profile screen");
		return false;
	}

	public boolean stateDisplayed() {
		if (state.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + " State is displayed on edit profile screen as expected: " + state.getText());
			return true;
		} else
			System.out.println(RED_X + " Stateis not displayed on edit profile screen");
		return false;
	}

	public boolean zipcodeDisplayed() {
		if (zipCode.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + " Zip code is displayed on edit profile screen as expected: " + zipCode.getText());
			return true;
		} else
			System.out.println(RED_X + " Zip code is not displayed on edit profile screen");
		return false;
	}

	public boolean countryDisplayed() {
		if (country.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + " Country is displayed on edit profile screen as expected: " + country.getText());
			return true;
		} else
			System.out.println(RED_X + " Country is not displayed on edit profile screen");
		return false;
	}

	public boolean shippingPhoneDisplayed() {
		if (shippingPhone.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Shipping phone is displayed on edit profile screen as expected: "
					+ shippingPhone.getText());
			return true;
		} else
			System.out.println(RED_X + " Shipping phone is not displayed on edit profile screen");
		return false;
	}

	public boolean employeSalesCodeDisplayed() {
		if (employeeSalesCode.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Employee sales code is displayed on edit profile screen as expected: "
					+ employeeSalesCode.getText());
			return true;
		} else
			System.out.println(RED_X + " Employee sales code is not displayed on edit profile screen");
		return false;
	}

	public boolean saveChangesBtnDisplayed() {
		if (saveChangesBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + "Save Changes button is displayed on edit profile screen as expected ");
			return true;
		} else
			System.out.println(RED_X + " Save Changes button is not displayed on edit profile screen");
		return false;
	}

}
