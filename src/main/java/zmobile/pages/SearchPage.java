package zmobile.pages;

import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import zmobile.utils.TestDataReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.Keys;

public class SearchPage extends BasePage {

	public SearchPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.zumiez:id/back_button")
	@AndroidFindBy(id = "com.zumiez.sandbox:id/back_button")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'left back btn'")
	private WebElement leftBackBtn;

	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeTextField'")
	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement searchInputField;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'dress')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'dress'")
	private WebElement searchOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Search results for: dress']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Search results for: dress'")
	private WebElement searchResults;

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'No Results Found'")
	private WebElement noResultsFound;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'items found')]")
	@iOSXCUITFindBy(iOSNsPredicate = "value CONTAINS 'items found'")
	private WebElement numberOfItemsFound;

	@AndroidFindBy(accessibility = "Clear text")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Close'")
	private WebElement closeBtn;

	public void selectSearchOption() {
		searchOption.click();
	}

	public void selectCloseBtn() {
		closeBtn.click();
	}

	public void clickSearchInputField() {
		searchInputField.click();
	}

	public boolean numberOfItemsFound() {
		if (numberOfItemsFound.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Number of items found is displayed on search screen as expected - "
					+ numberOfItemsFound.getText());
			return true;
		} else
			System.out.println(RED_X + " Number of items found is not displayed on search screen");
		return false;
	}

	public boolean noResultsFound() {
		if (noResultsFound.isDisplayed()) {
			System.out.println(GREEN_CHECK + " The following text is displayed as expected on search screen - "
					+ noResultsFound.getText());
			return true;
		} else
			System.out.println(RED_X + " Text No Results Found is missing");
		return false;
	}

	public boolean searchInputFieldDisplayed() {
		if (searchInputField.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Search input field is displayed on search screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Search input field is not displayed on search screen");
		return false;
	}

	public boolean searchResultsDisplayed() {
		if (searchResults.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Search results are displayed on search screen as expected - "
					+ searchResults.getText());
			return true;
		} else
			System.out.println(RED_X + " Search results are not displayed on search screen");
		return false;
	}

	private String getLeftBackBtnId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/back_button";
		case "prod":
		case "production":
			return "com.zumiez:id/back_button";
		default:
			return "com.zumiez.sandbox:id/back_button";
		}
	}

	public void validateLeftBackBtn() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == 'left back btn'")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " TEST PASSED: Left back button found");
				} else {
					System.out.println(RED_X + " TEST FAILED: Left back button not found");
				}
			}

			else {
				String id = getLeftBackBtnId();
				System.out.println("DEBUG: Looking for element with ID: " + id);

				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(id)));

				if (element != null && element.isEnabled()) {

					System.out.println(GREEN_CHECK + " TEST PASSED: Left back button found on product details screen");
				} else {
					System.out.println(RED_X + " TEST FAILED: Left back button is not found on product details screen");
				}
			}
		}

		catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get left back button");

		}
	}

	public WebElement getSearchResultsElement(String searchText) {
		if (driver instanceof IOSDriver) {
			String predicate = String.format("label == 'Search results for: %s'", searchText);
			return driver.findElement(AppiumBy.iOSNsPredicateString(predicate));
		} else {

			IntStream.range(0, 2).forEach(i -> ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER)));

			String xpath = String.format("//android.widget.TextView[@text='Search results for: %s']", searchText);
			return driver.findElement(AppiumBy.xpath(xpath));
		}
	}

	public boolean verifySearchResults(String searchText) {
		try {
			WebElement results = getSearchResultsElement(searchText);
			System.out.println("Search results for: " + searchText);
			return results.isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Search results not found for: " + searchText);
			return false;
		}
	}

	public WebElement getSearchInputField() {
		if (driver instanceof IOSDriver) {
			return driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField"));
		} else {
			return driver.findElement(AppiumBy.className("android.widget.EditText"));
		}
	}

	public void itemSearch(String searchText) throws InterruptedException {
		WebElement searchField = getSearchInputField();
		searchField.clear();
		Thread.sleep(3000);

		if (driver instanceof IOSDriver) {
			try {
				WebElement clearButton = driver.findElement(
						AppiumBy.iOSNsPredicateString("label == 'Clear text' OR type == 'XCUIElementTypeButton'"));
				clearButton.click();
				Thread.sleep(2000);
			} catch (Exception e) {
				searchField.sendKeys(Keys.chord(Keys.COMMAND, "a"));
				searchField.sendKeys(Keys.DELETE);
			}
		} else {
			searchField.clear();
		}

		searchField.click();
		Thread.sleep(2000);
		searchField.sendKeys(searchText);

		if (driver instanceof IOSDriver) {
			try {
				Thread.sleep(5000);
				List<WebElement> buttons = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton"));
				for (WebElement button : buttons) {
					String label = button.getAttribute("label");
					System.out.println("DEBUG: Found button with label: " + label);

					if (label != null && (label.toLowerCase().contains("search")
							|| label.toLowerCase().contains("traži") || label.toLowerCase().contains("return"))) {
						button.click();
						System.out.println("DEBUG: Clicked button: " + label);
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("DEBUG: Could not find search button, using RETURN key");
				searchInputField.sendKeys(Keys.RETURN);
			}
		} else {
			IntStream.range(0, 2).forEach(i -> ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER)));

		}

		System.out.println(GREEN_CHECK + " Entered search text and pressed Enter");
		Thread.sleep(5000);
	}
}
