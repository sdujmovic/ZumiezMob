package zmobile.pages;

import java.io.File;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import zmobile.utils.TestDataReader;

public class HomePage extends BasePage {

	public HomePage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"What's new\"]")
	@iOSXCUITFindBy(iOSNsPredicate = "label == \"What's new\"")
	private WebElement homePageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Keep exploring']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Keep exploring'")
	private WebElement keepExploring;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='FIND A STORE']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'FIND A STORE'")
	private WebElement findStore;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ZUMIEZ.COM']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'ZUMIEZ.COM'")
	private WebElement zumiezcom;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONTACT US']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'CONTACT US'")
	private WebElement contactUs;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.zumiez.sandbox:id/myFavoriteStoreTitle']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.zumiez:id/myFavoriteStoreTitle']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[10]")
	private WebElement favoriteStore;

	@AndroidFindBy(id = "com.zumiez:id/myFavoriteStoreChatButton")
	@AndroidFindBy(id = "com.zumiez.sandbox:id/myFavoriteStoreChatButton")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'TALK'")
	private WebElement letsTalkBtn;

	public void clickLetsTalkBtn() {
		letsTalkBtn.click();
	}

	public void clickOnFavoriteStore() {
		favoriteStore.click();
	}

	public boolean homePageTitleDisplayed() {
		if (homePageTitle.isDisplayed()) {
			System.out.println("✓ Home page title is displayed");
			return true;
		} else {
			System.out.println("✗ Home page title is NOT displayed");
			return false;
		}
	}

	public boolean letsTalkBtnEnabled() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label CONTAINS 'TALK'")));
				if (element != null && element.isEnabled()) {
					System.out.println("✓ Let's talk button found");
					return true;
				} else {
					System.out.println("✗ Let's talk button not enabled");
					return false;
				}

			} else {
				String id = getLetsTalkBtnId();
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id(id)));
				System.out.println("✓ Lets talk button found " + element.getText());
				return element.isEnabled();
			}
		} catch (Exception e) {
			System.out.println("✗ Lets talk button not enabled " + e.getMessage());
			return false;
		}
	}

	public boolean favoriteStoreOrSetStoreEnabled() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[10]")));
				if (element != null && element.isEnabled()) {
					System.out.println("✓ Favorite store found");
					return true;
				} else {
					System.out.println("✗ Favorite store not enabled");
					return false;
				}
			} else {
				try {
					String favoriteStoreId = getFavoriteStoreResourceId();
					WebElement favoriteStore = driver.findElement(
							AppiumBy.xpath("//android.widget.TextView[@resource-id='" + favoriteStoreId + "']"));

					if (favoriteStore.isEnabled()) {
						System.out.println("✓ Favorite store found: " + favoriteStore.getText());
						return true;
					}
				} catch (Exception e) {
					System.out.println("Favorite store not found, checking for Set My Store button...");
				}

				try {
					String setStoreId = getSetMyStoreResourceId();
					WebElement setStoreBtn = driver.findElement(AppiumBy.id(setStoreId));

					if (setStoreBtn.isDisplayed()) {
						System.out.println("✓ Set My Store button found");
						return true;
					}
				} catch (Exception e) {
					System.out.println("✗ Neither favorite store nor Set My Store button found");
				}

				return false;
			}
		} catch (Exception e) {
			System.out.println("✗ Error checking store elements: " + e.getMessage());
			return false;
		}
	}

	private String getFavoriteStoreResourceId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/myFavoriteStoreTitle";
		case "prod":
		case "production":
			return "com.zumiez:id/myFavoriteStoreTitle";
		default:
			return "com.zumiez.sandbox:id/myFavoriteStoreTitle";
		}
	}

	private String getLetsTalkBtnId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/myFavoriteStoreChatButton";
		case "prod":
		case "production":
			return "com.zumiez:id/myFavoriteStoreChatButton";
		default:
			return "com.zumiez.sandbox:id/myFavoriteStoreChatButton";
		}
	}

	private String getSetMyStoreResourceId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/myStoreButton";
		case "prod":
		case "production":
			return "com.zumiez:id/myStoreButton";
		default:
			return "com.zumiez.sandbox:id/myStoreButton";
		}
	}

	public void takeHomeScreenScreenshot() {
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("src/test/resources/images/iOS/debug/home_screen.png"));
			System.out.println("Screenshot saved: home_screen.png");
		} catch (Exception e) {
			System.out.println("Could not take screenshot: " + e.getMessage());
		}
	}

	public void scrollToHomeScreenBottom() {
		scrollToElement("Keep exploring");
	}

	public boolean isKeepExploringTitleDisplayed() {
		if (keepExploring.isDisplayed()) {
			System.out.println("✓ Keep Exploring title is displayed");
			return true;
		} else {
			System.out.println("✗ Keep Exploring title is NOT displayed");
			return false;
		}
	}

	public boolean ifKeepExploringOptionsDisplayed() {
		if (findStore.isDisplayed() && zumiezcom.isDisplayed() && contactUs.isDisplayed()) {
			System.out.println("✓ Keep Exploring options - find store, zummiez.com and contact us are displayed");
			return true;
		} else {
			System.out.println("✗ Keep Exploring options - find store, zummiez.com and contact us are NOT displayed");
			return false;
		}
	}

	public void verifyStoreButtonLocation() {
		try {
			Point location = favoriteStore.getLocation();
			String label = favoriteStore.getAttribute("label");
			String name = favoriteStore.getAttribute("name");

			System.out.println(String.format("Store button - Label: %s, Name: %s, X: %d, Y: %d", label, name,
					location.getX(), location.getY()));

			if (location.getY() > 500) {
				System.out.println("WARNING: Button is in tab bar area! Wrong element selected.");
			} else {
				System.out.println("Correct button selected!");
			}
		} catch (Exception e) {
			System.out.println("Error verifying button: " + e.getMessage());
		}
	}

	public void verifyLetsTalkButtonLocation() {
		try {
			Point location = letsTalkBtn.getLocation();
			String label = letsTalkBtn.getAttribute("label");
			String name = letsTalkBtn.getAttribute("name");

			System.out.println(String.format("Store button - Label: %s, Name: %s, X: %d, Y: %d", label, name,
					location.getX(), location.getY()));

			if (location.getY() > 500) {
				System.out.println("WARNING: Button is in tab bar area! Wrong element selected.");
			} else {
				System.out.println("Correct button selected!");
			}
		} catch (Exception e) {
			System.out.println("Error verifying button: " + e.getMessage());
		}
	}

	public void verifyHomeScreenButtons() {
		try {
			List<WebElement> allButtons = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton"));

			System.out.println("=== ALL BUTTONS ON HOME SCREEN ===");
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

		Assert.assertTrue(favoriteStoreOrSetStoreEnabled());

	}
}
