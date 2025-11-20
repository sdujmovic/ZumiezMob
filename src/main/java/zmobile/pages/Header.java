package zmobile.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
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
import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;

public class Header extends BasePage {

	public Header(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='header_profile_image']")
	@iOSXCUITFindBy(iOSNsPredicate = "name == 'TabBar Item more'")
	private WebElement moreTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wishlists']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Wishlists'")
	private WebElement wishlistsTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='My bag']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'My Bag'")
	private WebElement myBagTitle;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Features']")
	private WebElement features;

	@AndroidFindBy(xpath = "//android.widget.ScrollView[@resource-id='com.zumiez.sandbox:id/root_view']/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton[1]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther/XCUIElementTypeButton[1]")
	private WebElement profileIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'points')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'pts'")
	private WebElement points;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[2]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeNavigationBar/**/XCUIElementTypeButton[-1]")
	private WebElement searchIcon;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.zumiez.sandbox:id/barcode_scan_icon']")
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.zumiez:id/barcode_scan_icon']")
	@iOSXCUITFindBy(accessibility = "header_scan")
	private WebElement zumiezScanIcon;

	public boolean featuresDisplayed() {
		if (features.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean profileIconDisplayed() {
		if (profileIcon.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean pointsDisplayed() {
		if (points.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean searchIconDisplayed() {
		if (searchIcon.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean headerForHomeScreen() {
		if (profileIcon.isDisplayed() && points.isDisplayed() && isSearchIconDisplayed()) {
			System.out.println(GREEN_CHECK
					+ " Profile icon, points and search icon are displayed in the header of Home screen as expected");
			return true;
		}

		else {
			System.out.println(
					RED_X + " Profile icon, points and search icon are NOT displayed in the header of Home screen");
			return false;
		}
	}

	public boolean headerForStashScreen() {
		if (profileEnabled() && points.isEnabled() && zumiezScanBtnEnabled()) {
			System.out.println(
					GREEN_CHECK + " Profile icon, points are displayed in the header of Stash screen as expected");
			return true;
		} else {
			System.out.println(RED_X + " Profile icon, points are NOT displayed in the header of Stash screen");
			return false;
		}
	}

	public boolean headerForShopScreen() {
		if (profileIcon.isDisplayed() && points.isDisplayed() && isSearchIconDisplayed()) {
			System.out.println(GREEN_CHECK
					+ " Profile icon, points and search icon are displayed in the header of Shop screen as expected");
			return true;
		} else
			System.out.println(
					RED_X + " Profile icon, points and search icon are NOT displayed in the header of Shop screen");
		return false;
	}

	public boolean isWishlistTitleDisplayed() {
		if (wishlistsTitle.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Wishlists title is displayed on the Wishlists screen");
			return true;
		} else
			System.out.println(RED_X + " Wishlists title is missing on the Wishlists screen");
		return false;
	}

	public boolean isMyBagTitleDisplayed() {
		if (myBagTitle.isDisplayed()) {
			System.out.println(GREEN_CHECK + " My bag title is displayed on the Bag screen");
			return true;
		} else
			System.out.println(RED_X + " My bag title is missing on the Bag screen");
		return false;
	}

	public boolean headerForWishlistScreen() {
		if (profileIcon.isDisplayed() && wishlistsTitle.isDisplayed() && isSearchIconDisplayed()) {
			System.out.println(GREEN_CHECK
					+ " Profile icon, Wishlists title and search icon are displayed in the header of Wishlists screen as expected");
			return true;
		}

		else {
			System.out.println(RED_X
					+ " Profile icon, Wishlists title and search icon are NOT displayed in the header of Wishlists screen");
			return false;
		}
	}

	public void debugProfileIcon() {
		List<WebElement> allButtons = driver.findElements(By.xpath("//XCUIElementTypeButton"));
		System.out.println("Total buttons found: " + allButtons.size());

		for (int i = 0; i < allButtons.size(); i++) {
			WebElement button = allButtons.get(i);
			try {
				String label = button.getAttribute("label");
				String name = button.getAttribute("name");
				String value = button.getAttribute("value");
				org.openqa.selenium.Rectangle rect = button.getRect();

				System.out.println(
						String.format("Profile Icon Button %d - Label: %s, Name: %s, Value: %s, X: %.2f, Y: %.2f", i,
								label, name, value, (double) rect.x, (double) rect.y));
			} catch (Exception e) {
				System.out.println("Button " + i + " - Could not get attributes");
			}
		}
	}

	public boolean zumiezScanBtnEnabled() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait
						.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("header_scan")));
				if (element != null && element.isEnabled()) {
					System.out.println(
							GREEN_CHECK + " Zumiez scan button found and enabled in the header of Stash screen");
					return true;
				} else {
					System.out
							.println(RED_X + " Zumiez scan button not found and enabled in the header of Stash screen");
					return false;
				}

			} else {

				String zumiezScanBtnId = getZumiezScanBtnResourceId();
				WebElement zumiezScanBtn = driver.findElement(
						AppiumBy.xpath("//android.widget.ImageButton[@resource-id='" + zumiezScanBtnId + "']"));

				if (zumiezScanBtn != null && zumiezScanBtn.isEnabled()) {
					System.out.println(GREEN_CHECK + " Zumiez scan button found in the header of Stash screen ");
					return true;
				}

				else {
					System.out.println(RED_X + " Zumiez scan button not enabled in the header of Stash screen");
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println(RED_X + " Zumiez scan button not found or not enabled in the header of Stash screen: "
					+ e.getMessage());
			return false;
		}
	}

	private String getZumiezScanBtnResourceId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/barcode_scan_icon";
		case "prod":
		case "production":
			return "com.zumiez:id/barcode_scan_icon";
		default:
			return "com.zumiez.sandbox:id/barcode_scan_icon";
		}
	}

	public boolean profileEnabled() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
						AppiumBy.iOSClassChain("**/XCUIElementTypeOther/XCUIElementTypeButton[1]")));
				if (element != null && element.isEnabled()) {
					System.out.println(GREEN_CHECK + " Profile icon found in stash header");
					return true;
				} else {
					System.out.println(RED_X + " Profile icon not enabled in stash header");
					return false;
				}

			} else {
				String id = "com.zumiez.sandbox:id/stash_header";
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id(id)));
				System.out.println(GREEN_CHECK + " Profile icon found in stash header");
				return element.isEnabled();
			}
		} catch (Exception e) {
			System.out.println(RED_X + " Profile icon not enabled in stash header");
			return false;
		}
	}

	public void verifySearchIconLocation() {
		try {
			Point location = searchIcon.getLocation();
			String label = searchIcon.getAttribute("label");
			String name = searchIcon.getAttribute("name");

			System.out.println(String.format("Search Button - Label: %s, Name: %s, X: %d, Y: %d", label, name,
					location.getX(), location.getY()));

			if (location.getY() > 500) {
				System.out.println("WARNING: Button is in tab bar area! Wrong element selected.");
			} else {
				System.out.println("Correct Search Icon button selected!");
			}
		} catch (Exception e) {
			System.out.println("Error verifying Search Icon button: " + e.getMessage());
		}
	}

	public void verifyPointsLocation() {
		try {
			Point location = points.getLocation();
			String label = points.getAttribute("label");
			String name = points.getAttribute("name");

			System.out.println(String.format("Points Location - Label: %s, Name: %s, X: %d, Y: %d", label, name,
					location.getX(), location.getY()));

			if (location.getY() > 500) {
				System.out.println("WARNING: Button is in tab bar area! Wrong element selected.");
			} else {
				System.out.println("Correct Points location selected!");
			}
		} catch (Exception e) {
			System.out.println("Error verifying Points location: " + e.getMessage());
		}
	}

	public void verifyProfileIconLocation() {
		try {
			Point location = profileIcon.getLocation();
			String label = profileIcon.getAttribute("label");
			String name = profileIcon.getAttribute("name");

			System.out.println(String.format("Button - Label: %s, Name: %s, X: %d, Y: %d", label, name, location.getX(),
					location.getY()));

			if (location.getY() > 500) {
				System.out.println("WARNING: Button is in tab bar area! Wrong element selected.");
			} else {
				System.out.println("Correct Profile Icon button selected!");
			}
		} catch (Exception e) {
			System.out.println("Error verifying Profile Icon button: " + e.getMessage());
		}
	}

	public void selectProfileIcon() {
		try {
			System.out.println("Finding profile icon...");
			wait.until(ExpectedConditions.elementToBeClickable(profileIcon));

			System.out.println("Profile icon found at position: " + profileIcon.getLocation().getX() + ", "
					+ profileIcon.getLocation().getY());

			profileIcon.click();

			System.out.println("Profile icon clicked successfully");
			Thread.sleep(1000);

		} catch (Exception e) {
			System.err.println("Failed to click profile icon: " + e.getMessage());

			if (driver instanceof IOSDriver) {
				System.out.println("Fallback: Using iOS button index search...");
				try {
					List<WebElement> buttons = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton"));

					System.out.println("Total buttons found: " + buttons.size());

					if (buttons.size() > 5) {
						WebElement profileBtn = buttons.get(5);
						profileBtn.click();
						System.out.println("Profile button clicked using fallback method");
						Thread.sleep(1000);
					} else {
						throw new Exception("Not enough buttons found for fallback");
					}
				} catch (Exception fallbackError) {
					System.err.println("Fallback also failed: " + fallbackError.getMessage());
					throw new RuntimeException("Failed to click profile icon on both primary and fallback methods",
							fallbackError);
				}
			} else {
				throw new RuntimeException("Failed to click profile icon: " + e.getMessage(), e);
			}
		}

	}

	public void selectMoreTab() {
		try {
			System.out.println("Clicking 'more' tab...");
			waitForElementToBeClickable(moreTab);
			moreTab.click();
			System.out.println("'More' tab clicked successfully");
			Thread.sleep(1000);
		} catch (Exception e) {
			System.err.println("Failed to click 'more' tab: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public WebElement getSearchIconOnShopScreen() {
		try {
			List<WebElement> buttons = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton"));

			for (int i = 0; i < buttons.size(); i++) {
				WebElement btn = buttons.get(i);
				System.out.println(
						"Button " + i + ": " + "x=" + btn.getLocation().getX() + ", y=" + btn.getLocation().getY());
			}

			for (WebElement btn : buttons) {
				int x = btn.getLocation().getX();
				int y = btn.getLocation().getY();
				if (x > 300 && x < 330 && y > 160 && y < 190) {
					System.out.println(GREEN_CHECK + " Found search button at x=" + x + ", y=" + y);
					return btn;
				}
			}
		} catch (Exception e) {
			System.out.println(RED_X + " Error finding search button: " + e.getMessage());
		}
		return null;
	}

	public WebElement getSearchIcon1() {
		try {
			List<WebElement> buttons = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton"));

			for (WebElement btn : buttons) {
				int x = btn.getLocation().getX();
				int y = btn.getLocation().getY();

				if (x > 360 && x < 390 && y > 50 && y < 80) {
					System.out.println("✓ Found search button at x=" + x + ", y=" + y);
					return btn;
				}
			}
		} catch (Exception e) {
			System.out.println("✗ Error: " + e.getMessage());
		}
		return null;
	}

	public WebElement getSearchIcon() {
		try {
			if (driver instanceof IOSDriver) {
				List<WebElement> buttons = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton"));

				for (WebElement btn : buttons) {
					int x = btn.getLocation().getX();
					int y = btn.getLocation().getY();

					if (x > 360 && x < 390 && y > 50 && y < 80) {
						System.out.println("✓ Found search button at x=" + x + ", y=" + y);
						return btn;
					}
				}
			} else {
				// Android implementation
				WebElement searchBtn = driver
						.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='header_scan']"));
				System.out.println(GREEN_CHECK + " Found search button on Android");
				return searchBtn;
			}
		} catch (Exception e) {
			System.out.println(RED_X + " Error finding search button: " + e.getMessage());
		}
		return null;
	}

	public boolean isSearchIconDisplayed() {
		try {
			WebElement searchBtn = getSearchIcon();
			if (searchBtn != null && searchBtn.isDisplayed()) {
				System.out.println(GREEN_CHECK + " TEST PASSED: Search icon is displayed");
				return true;
			} else {
				System.out.println(RED_X + " TEST FAILED: Search icon not displayed");
				return false;
			}
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: " + e.getMessage());
			return false;
		}
	}

	public void selectSearchIcon() {
		try {
			WebElement searchBtn = getSearchIcon();
			if (searchBtn != null && searchBtn.isDisplayed()) {
				searchBtn.click();
			} else {
				System.out.println(GREEN_CHECK + " TEST FAILED: Search icon not displayed");

			}
		} catch (Exception e) {
			System.out.println(GREEN_CHECK + " TEST FAILED: " + e.getMessage());

		}
	}
}
