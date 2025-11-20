package zmobile.pages;

import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import zmobile.utils.TestDataReader;

public class ProductDetailsPage extends BasePage {

	public ProductDetailsPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.zumiez.sandbox:id/image_page")
	@AndroidFindBy(id = "com.zumiez:id/image_page")
	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeImage' AND visible == 1")
	private WebElement productImage;

	@AndroidFindBy(id = "com.zumiez.sandbox:id/share_button")
	@AndroidFindBy(id = "com.zumiez:id/share_button")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'share icn'")
	private WebElement shareIcon;

	@AndroidFindBy(id = "com.zumiez.sandbox:id/title_view")
	@AndroidFindBy(id = "com.zumiez:id/title_view")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Empyre Loose Fit Khaki Cargo Skate Pants'")
	private WebElement productTitleOnProductDetailsPage;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, '$')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS '$'")
	private WebElement productPrice;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Size:']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Size'")
	private WebElement size;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD TO MY BAG']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'ADD TO MY BAG'")
	private WebElement addToMyBagBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='store chat']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'store chat'")
	private WebElement storeChatBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='add to my wishlist']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'add to my wishlist'")
	private WebElement addToMyWishlistBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='remove from wishlist']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'add to my wishlist'")
	private WebElement removeFromWishlistBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Features']")
	@iOSXCUITFindBy(iOSNsPredicate = "value CONTAINS 'Features'")
	private WebElement features;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Size And Fit']")
	@iOSXCUITFindBy(iOSNsPredicate = "value CONTAINS 'Size And Fit'")
	private WebElement sizeAndFit;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Got questions about this product?']")
	@iOSXCUITFindBy(iOSNsPredicate = "value CONTAINS 'Ask your store about this product...'")
	private WebElement askYourStore;

	@AndroidFindBy(id = "com.zumiez.sandbox:id/send_button")
	@AndroidFindBy(id = "com.zumiez:id/send_button")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'ic send'")
	private WebElement sendMsgBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='May We Suggest']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'May We Suggest'")
	private WebElement mayWeSuggest;

	public void debuggFeatures() {
		List<WebElement> unlabeledButtons = driver
				.findElements(By.xpath("//XCUIElementTypeButton[@visible='true' and not(@label) and not(@name)]"));
		WebElement features = unlabeledButtons.get(2);
	}

	public void findAllGroups() {
		List<WebElement> groups = driver.findElements(By.xpath("//XCUIElementTypeGroup"));
		System.out.println("Found " + groups.size() + " groups");

		for (WebElement group : groups) {
			try {
				WebElement button = group.findElement(By.xpath(".//XCUIElementTypeButton"));
				String label = button.getAttribute("label");
				String name = button.getAttribute("name");
				System.out.println("Found button in group - Label: " + label + ", Name: " + name);
			} catch (Exception e) {
			}
		}
	}

	public void visibleOnProductDetailsPage() throws IOException {
		try {
			List<WebElement> allElements = driver.findElements(By.xpath(
					"//*[contains(@value, 'Features') or contains(@label, 'Features') or contains(@name, 'Features')]"));
			System.out.println("Elements with 'Features': " + allElements.size());

			List<WebElement> others = driver.findElements(By.xpath("//XCUIElementTypeOther"));
			System.out.println("Total Other elements: " + others.size());

			List<WebElement> groups = driver.findElements(By.xpath("//XCUIElementTypeGroup"));
			System.out.println("Total Groups: " + groups.size());

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public boolean askYourStoreEnabled() {
		if (askYourStore.isEnabled()) {
			System.out.println(GREEN_CHECK
					+ " Ask your store field to enter message is enabled on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Ask your store field is not enabled not enabled on product details screen");
		return false;
	}

	public boolean sendMsgBtnEnabled() {
		if (sendMsgBtn.isEnabled()) {
			System.out.println(GREEN_CHECK + " Send message button is enabled on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Send message button is not enabled not enabled on product details screen");
		return false;
	}

	public boolean mayWeSuggestTitleDisplayed() {
		if (mayWeSuggest.isDisplayed()) {
			System.out
					.println(GREEN_CHECK + " May we suggest title is displayed on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + " May we suggest title is not displayed not enabled on product details screen");
		return false;
	}

	public boolean featuresEnabled() {
		if (features.isEnabled()) {
			System.out.println(GREEN_CHECK + " Features are enabled on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Features are not enabled on product details screen");
		return false;
	}

	public boolean sizeAndFitEnabled() {
		if (sizeAndFit.isEnabled()) {
			System.out.println(GREEN_CHECK + " Size And Fit is enabled on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Size And Fit is not enabled on product details screen");
		return false;
	}

	private String getProductTitleId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/title_view";
		case "prod":
		case "production":
			return "com.zumiez:id/title_view";
		default:
			return "com.zumiez.sandbox:id/title_view";
		}
	}

	private String getProductImageId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/image_page";
		case "prod":
		case "production":
			return "com.zumiez:id/image_page";
		default:
			return "com.zumiez.sandbox:id/image_page";
		}
	}

	private String getShareIconId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/share_button";
		case "prod":
		case "production":
			return "com.zumiez:id/share_button";
		default:
			return "com.zumiez.sandbox:id/share_button";
		}
	}

	public void keepScrollingDown() throws InterruptedException {
		if (driver instanceof IOSDriver) {
			scrollToElementIOS("May We Suggest");
		} else {
			scrollToElementAndroid("Got questions about this product?");
		}
	}

	public void validateProductImage() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
						AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeImage' AND visible == 1")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " TEST PASSED: Product image found");
				} else {
					System.out.println(RED_X + " TEST FAILED: Product image not found");
				}
			}

			else {
				String id = getProductImageId();
				System.out.println("DEBUG: Looking for element with ID: " + id);

				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(id)));

				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " TEST PASSED: Product image found on product details screen");
				} else {
					System.out.println(RED_X + " TEST FAILED: Product image is not found on product details screen");
				}
			}
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get product image - ");
		}
	}

	public void validateShareIcon() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == 'share icn'")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " TEST PASSED: Share icon found");
				} else {
					System.out.println(RED_X + " TEST FAILED: Share icon not found");
				}
			}

			else {
				String id = getShareIconId();
				System.out.println("DEBUG: Looking for element with ID: " + id);

				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(id)));

				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " TEST PASSED: Share icon found on product details screen");
				} else {
					System.out.println(RED_X + " TEST FAILED: Share icon is not found on product details screen");
				}
			}
		}

		catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get share icon");
		}
	}

	public void validateProductTitle(String productName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == '" + productName + "'")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " TEST PASSED: Product title found - '" + element.getText() + "'");
				} else {
					System.out.println(RED_X + " TEST FAILED: Product title is empty");
				}
			} else {
				String id = getProductTitleId();
				System.out.println("DEBUG: Looking for element with ID: " + id);

				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(id)));

				if (element != null && element.isEnabled()) {
					String displayedTitle = element.getText();

					if (displayedTitle == null || displayedTitle.isEmpty()) {
						displayedTitle = element.getAttribute("text");
					}

					if (displayedTitle == null || displayedTitle.isEmpty()) {
						displayedTitle = element.getAttribute("resource-id");
					}

					if (displayedTitle == null || displayedTitle.isEmpty()) {
						displayedTitle = element.getAttribute("name");
					}

					System.out.println(GREEN_CHECK + " TEST PASSED: Product title found on product details screen - '"
							+ displayedTitle + "'");
				} else {
					System.out.println(RED_X + " TEST FAILED: Product title is not found on product details screen");
				}
			}
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get product title - " + e.getMessage());
		}
	}

	public void validateProductPrice(String productPrice) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == '" + productPrice + "'")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " TEST PASSED: Product price found - '" + element.getText() + "'");
				} else {
					System.out.println(RED_X + " TEST FAILED: Product price is empty");
				}
			} else {
				WebElement element = wait.until(ExpectedConditions
						.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[contains(@text, '$')]")));

				if (element != null && element.isEnabled()) {
					String displayedTitle = element.getText();

					if (displayedTitle == null || displayedTitle.isEmpty()) {
						displayedTitle = element.getAttribute("text");
					}

					if (displayedTitle == null || displayedTitle.isEmpty()) {
						displayedTitle = element.getAttribute("content-desc");
					}

					if (displayedTitle == null || displayedTitle.isEmpty()) {
						displayedTitle = element.getAttribute("name");
					}

					System.out.println(GREEN_CHECK + " TEST PASSED: Product price found on product details screen - '"
							+ displayedTitle + "'");
				} else {
					System.out.println(RED_X + " TEST FAILED: Product price is not found on product details screen");
				}
			}
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get product price - " + e.getMessage());
		}
	}

	public boolean productImageDisplayed() {
		if (productImage.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + "TEST PASSED: Product image is displayed on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + "TEST FAILED: Product image is not displayed on product details screen");
		return false;
	}

	public boolean shareIconDisplayed() {
		if (shareIcon.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + "TEST PASSED: Share icon is displayed on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + "TEST FAILED: Share icon is not displayed on product details screen");
		return false;
	}

	public boolean sizeDisplayed() {
		if (size.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Product size is displayed on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Product size is not displayed on product details screen");
		return false;
	}

	public boolean addToMyBagBtnDisplayed() {
		if (addToMyBagBtn.isDisplayed()) {
			System.out
					.println(GREEN_CHECK + " Add to My Bag Button is displayed on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Add to My Bag Button is not displayed on product details screen");
		return false;
	}

	public boolean storeChatBtnDisplayed() {
		if (storeChatBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Store Chat Button is displayed on product details screen as expected");
			return true;
		} else
			System.out.println(RED_X + " Store Chat Button is not displayed on product details screen");
		return false;
	}

	public boolean isWishlistButtonDisplayed() {
		try {
			System.out.println(GREEN_CHECK + " Wishlist button is displayed on product details screen as expected");
			return addToMyWishlistBtn.isDisplayed();
		} catch (NoSuchElementException e) {
			try {
				System.out.println(GREEN_CHECK + " Wishlist button is displayed on product details screen as expected");
				return removeFromWishlistBtn.isDisplayed();
			} catch (NoSuchElementException ex) {
				System.out.println(RED_X + " Wishlist button is not displayed on product details screen");
				return false;
			}
		}
	}

	public String getWishlistButtonText() {
		try {
			if (addToMyWishlistBtn.isDisplayed()) {
				System.out.println(GREEN_CHECK + " Add to my wishlist button is found on product details screen");
				return addToMyWishlistBtn.getText();
			}
		} catch (NoSuchElementException e) {
			System.out.println(RED_X + " Wishlist button is not found on product details screen");
		}

		try {
			if (removeFromWishlistBtn.isDisplayed()) {
				System.out.println(GREEN_CHECK + " Remove from wishlist button is found on product details screen");
				return removeFromWishlistBtn.getText();
			}
		} catch (NoSuchElementException e) {
			System.out.println(RED_X + " Wishlist button is not found on product details screen");
		}
		return "";
	}

	public WebElement getFeaturesSection() {
		return driver.findElement(By.xpath("//XCUIElementTypeStaticText[@label='Features']"));
	}

	public WebElement getSizeAndFitSection() {
		return driver.findElement(
				MobileBy.iOSNsPredicateString("label == 'Size And Fit' AND type == 'XCUIElementTypeStaticText'"));
	}

	public boolean isFeaturesDisplayed() {
		try {
			return getFeaturesSection().isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isSizeAndFitDisplayed() {
		try {
			return getSizeAndFitSection().isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
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

	public void scrollToElementIOS(String elementLabel) throws InterruptedException {
		System.out.println("Using iOS mobile scroll to find: " + elementLabel);

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
				dismissKeyboardByTap();

			} catch (Exception e) {
				System.err.println("✗ Error during scroll attempt " + scrollAttempt + ": " + e.getMessage());
			}
		}

		if (!elementFound) {
			throw new RuntimeException(
					"✗ Element '" + elementLabel + "' not found after " + maxScrollAttempts + " scroll attempts");
		}
	}

	public WebElement scrollToFeatures() throws InterruptedException {
		scrollToElementIOS("Features");
		return driver.findElement(
				MobileBy.iOSNsPredicateString("label == 'Features' AND type == 'XCUIElementTypeStaticText'"));
	}

	public void debugPrintVisibleElements() {
		System.out.println("========== VISIBLE ELEMENTS ==========");
		List<WebElement> allElements = driver.findElements(MobileBy.iOSNsPredicateString("visible == true"));

		for (int i = 0; i < Math.min(allElements.size(), 20); i++) {
			WebElement element = allElements.get(i);
			String label = element.getAttribute("label");
			String type = element.getAttribute("type");
			System.out.println(i + ": " + type + " - " + label);
		}
		System.out.println("======================================");
	}

	private void dismissKeyboard() {
		try {
			if (isKeyboardShown()) {
				System.out.println("Keyboard is shown, dismissing...");
				hideKeyboard();
				Thread.sleep(500);
				System.out.println(GREEN_CHECK + " Keyboard dismissed");
			}
		} catch (Exception e) {
			System.out.println("No keyboard to dismiss or error: " + e.getMessage());
		}
	}

	private void dismissKeyboardByTap() {
		try {
			org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			int x = size.width / 2;
			int y = size.height / 4;

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence tap = new Sequence(finger, 1);
			tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(Arrays.asList(tap));

			Thread.sleep(500);
			System.out.println(GREEN_CHECK + " Tapped to dismiss keyboard");
		} catch (Exception e) {
			System.out.println("Error dismissing keyboard: " + e.getMessage());
		}
	}

	public WebElement findFeaturesElement() {

		try {
			return wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeButton[@name='Features']")));
		} catch (Exception e1) {

			try {
				return wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Features']")));
			} catch (Exception e2) {

				try {
					return wait.until(
							ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@name, 'Features')]")));
				} catch (Exception e3) {

					return driver.findElement(
							MobileBy.iOSNsPredicateString("name CONTAINS 'Features' OR label CONTAINS 'Features'"));
				}
			}
		}
	}

	public void verifyProductDetailsScreenButtons() {

		try {
			List<WebElement> allButtons = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton"));

			System.out.println("=== ALL BUTTONS ON PRODUCT DETAILS SCREEN ===");
			for (int i = 0; i < allButtons.size(); i++) {
				try {
					String label = allButtons.get(i).getAttribute("label");
					String name = allButtons.get(i).getAttribute("name");
					Point loc = allButtons.get(i).getLocation();
					boolean visible = allButtons.get(i).isDisplayed();

					System.out.println(String.format("Button %d - Label: %s, Name: %s, Visible: %s, X: %.2f, Y: %.2f",
							i, label, name, visible, (float) loc.getX(), (float) loc.getY()));
				} catch (Exception e) {
					System.out.println("Button " + i + " - Error: " + e.getMessage());
				}
			}
			System.out.println("=== END BUTTON LIST ===");
		} catch (Exception e) {
			System.out.println("Error listing buttons: " + e.getMessage());
		}

	}

	public void debuggProductDetailsScreen() {
		List<WebElement> allButtons = driver.findElements(By.xpath("//XCUIElementTypeButton"));
		for (int i = 0; i < allButtons.size(); i++) {
			System.out.println("Button " + i + ":");
			System.out.println("  Text: " + allButtons.get(i).getText());
			System.out.println("  Label: " + allButtons.get(i).getAttribute("label"));
			System.out.println("  Name: " + allButtons.get(i).getAttribute("name"));
			System.out.println("  Value: " + allButtons.get(i).getAttribute("value"));
			System.out.println("  Visible: " + allButtons.get(i).getAttribute("visible"));
			System.out.println("---");
		}
	}

	public boolean hasFeaturesSection() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			List<WebElement> elements;

			if (driver instanceof IOSDriver) {
				elements = driver.findElements(AppiumBy.iOSNsPredicateString("value CONTAINS 'Features'"));
			} else {
				elements = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Features']"));
			}

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return elements.size() > 0;

		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return false;
		}
	}

	public boolean hasSizeAndFitSection() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			List<WebElement> elements;

			if (driver instanceof IOSDriver) {
				elements = driver.findElements(AppiumBy.iOSNsPredicateString("value CONTAINS 'Size And Fit'"));
			} else {
				elements = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Size And Fit']"));
			}

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return elements.size() > 0;

		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return false;
		}
	}

	public void displayProductSectionsSummary() {
		boolean hasFeatures = hasFeaturesSection();
		boolean hasSizeAndFit = hasSizeAndFitSection();

		System.out.println("\n" + "=".repeat(50));
		System.out.println("PRODUCT SECTIONS SUMMARY");
		System.out.println("=".repeat(50));

		if (hasFeatures && hasSizeAndFit) {
			System.out.println(GREEN_CHECK + " This product has BOTH sections:");
			System.out.println("  • Features");
			System.out.println("  • Size And Fit");
		} else if (hasFeatures && !hasSizeAndFit) {
			System.out.println(GREEN_CHECK + " This product has ONLY ONE section:");
			System.out.println("  • Features");
		} else if (!hasFeatures && hasSizeAndFit) {
			System.out.println(GREEN_CHECK + " This product has ONLY ONE section:");
			System.out.println("  • Size And Fit");
		} else {
			System.out.println(GREEN_CHECK + " This product does NOT have any expandable sections");
		}

		System.out.println("=".repeat(50) + "\n");
	}

}
