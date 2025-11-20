package zmobile.pages;

import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import zmobile.utils.TestDataReader;

public class ShopPage extends BasePage {

	public ShopPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Shop the full collection']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Shop the full collection'")
	private WebElement shopFullCollection;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Fresh picks']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Fresh picks'")
	private WebElement shopTitle;

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Empyre Loose Fit Khaki Cargo Skate Pants'")
	private WebElement productTitleOnShopPage;

	public void selectProduct() {
		productTitleOnShopPage.click();
	}

	public String validateShopTitle() {
		try {
			String titleText = shopTitle.getText();
			if (titleText != null && !titleText.isEmpty()) {
				System.out.println(GREEN_CHECK + " TEST PASSED: Shop title found - '" + titleText + "'");
			} else {
				System.out.println(RED_X + " TEST FAILED: Shop title is empty");
			}
			return titleText;
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get shop title - " + e.getMessage());
			return null;
		}
	}

	public String validateShopFullCollectionText() {
		try {
			String text = shopFullCollection.getText();
			if (text != null && !text.isEmpty()) {
				System.out.println(GREEN_CHECK + " TEST PASSED: Shop full collection text found - '" + text + "'");
			} else {
				System.out.println(RED_X + " TEST FAILED: Shop title is empty");
			}
			return text;
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get shop full collection text - " + e.getMessage());
			return null;
		}
	}

	public void scrollToAndClickProductAndroid(String productName) {
		try {
			String scrollableList = getScrollableResourceId();

			String uiScrollable = "new UiScrollable(new UiSelector().resourceId(\"" + scrollableList + "\"))"
					+ ".setMaxSearchSwipes(50)" + ".scrollIntoView(new UiSelector().text(\"" + productName + "\"))";

			driver.findElement(AppiumBy.androidUIAutomator(uiScrollable)).click();
			System.out.println(GREEN_CHECK + " Found product: " + productName);

		} catch (Exception e) {
			System.out.println(RED_X + " Product not found in scrollable view: " + productName);
			e.printStackTrace();
		}
	}

	private void scrollToAndClickProductiOS(String productName) throws InterruptedException {
		System.out.println("Using iOS mobile scroll to find and click product: " + productName);

		boolean elementFound = false;
		int maxScrollAttempts = 20;
		int scrollAttempt = 0;

		while (!elementFound && scrollAttempt < maxScrollAttempts) {
			try {
				List<WebElement> elements = driver
						.findElements(MobileBy.iOSNsPredicateString("label CONTAINS '" + productName + "'"));

				if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
					System.out.println(
							GREEN_CHECK + " Product '" + productName + "' found after " + scrollAttempt + " scroll(s)");
					elements.get(0).click();
					System.out.println(GREEN_CHECK + " Clicked on product: " + productName);

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
			throw new RuntimeException(
					RED_X + " Product '" + productName + "' not found after " + maxScrollAttempts + " scroll attempts");
		}
	}

	private String getScrollableResourceId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/recycler_view";
		case "prod":
		case "production":
			return "com.zumiez:id/recycler_view";
		default:
			System.out.println("Unknown build type: " + buildType + ", using default");
			return "com.zumiez.sandbox:id/recycler_view";
		}
	}

	public void findProductByText(String productName) {
		String platformName = driver.getCapabilities().getPlatformName().toString();

		System.out.println("Platform: " + platformName);
		System.out.println("Scrolling to product with text: " + productName);

		try {
			if ("ANDROID".equalsIgnoreCase(platformName)) {
				scrollToAndClickProductAndroid(productName);
			} else if ("IOS".equalsIgnoreCase(platformName)) {
				scrollToAndClickProductiOS(productName);

			} else {
				throw new RuntimeException("Unsupported platform: " + platformName);
			}
		} catch (Exception e) {
			System.err.println("Error scrolling to product: " + e.getMessage());
			throw new RuntimeException("Failed to scroll to product: " + productName, e);
		}
	}

}
