package zmobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import zmobile.config.ConfigReader;
import zmobile.utils.TestDataReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.awt.Dimension;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;

public class BasePage {

	protected AppiumDriver driver;
	protected WebDriverWait wait;

	public BasePage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
	}

	protected void waitForElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected void sendKeys(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
	}

	protected String getText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	protected boolean isDisplayed(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
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

	public void pressBackButton() {
		try {
			if (driver instanceof AndroidDriver) {
				AndroidDriver androidDriver = (AndroidDriver) driver;
				androidDriver.executeScript("mobile: pressKey", Map.of("keycode", 4)); // 4 is BACK key
				System.out.println("Back button pressed to hide keyboard");
			}
		} catch (Exception e) {
			System.out.println("Could not press back button: " + e.getMessage());
		}
	}

	public boolean isKeyboardShown() {
		try {
			if (driver instanceof AndroidDriver) {
				return ((AndroidDriver) driver).isKeyboardShown();
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public void scrollToElement(String elementText) {
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

	public void scrollToElementAndroid1(String text) {

		try {
			String scrollableList = "com.zumiez.sandbox:id/recycler_view";
			String uiScrollable = "new UiScrollable(new UiSelector().resourceId(\"" + scrollableList + "\"))"
					+ ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";

			driver.findElement(AppiumBy.androidUIAutomator(uiScrollable));

		} catch (Exception e) {
			System.out.println("Button not found in scrollable view");
			e.printStackTrace();
		}
	}

	public void scrollToElementAndroid(String text) {
		try {
			String scrollableList = getScrollableResourceIdByBuildType();
			String uiScrollable = "new UiScrollable(new UiSelector().resourceId(\"" + scrollableList + "\"))"
					+ ".setMaxSearchSwipes(50)" + ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";

			driver.findElement(AppiumBy.androidUIAutomator(uiScrollable));
			System.out.println(GREEN_CHECK + " Found element: " + text);

		} catch (Exception e) {
			System.out.println(RED_X + " Button not found in scrollable view: " + text);
			e.printStackTrace();
		}
	}

	private String getScrollableResourceIdByBuildType() {
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
}
