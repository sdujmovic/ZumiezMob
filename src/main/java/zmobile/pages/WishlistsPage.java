package zmobile.pages;

import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class WishlistsPage extends BasePage {

	public WishlistsPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[9]")
	@AndroidFindBy(xpath = "(//android.widget.Button)[9]")
	private WebElement shareBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SHOP']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'SHOP'")
	private WebElement shopTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='REWARDS']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'REWARDS'")
	private WebElement rewardsTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Find items at']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Find items at'")
	private WebElement findItemsAtStore;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch")
	@AndroidFindBy(id = "com.zumiez.sandbox:id/local_store_switch")
	private WebElement switchElement;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='SEND TO STORE CHAT']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'SEND TO STORE CHAT'")
	private WebElement sendToStoreChatBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='VIEWING YOUR STORE INVENTORY']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'VIEWING YOUR STORE INVENTORY'")
	private WebElement viewStoreInventoryTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'In stock today')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'In stock today'")
	private WebElement inStock;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Out of stock')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'Out of stock'")
	private WebElement outOfStock;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='AVAILABLE POINTS']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'AVAILABLE POINTS'")
	private WebElement availablePointsTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'pts')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'pts'")
	private WebElement numberOfPoints;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'item')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'item'")
	private WebElement numberOfRewards;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='0 items']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == '0 items'")
	private WebElement noRewardsAdded;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No Wishlists found']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'No Saved Rewards Found'")
	private WebElement noSavedRewardsFound;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No Wishlists found']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'No Wishlists found'")
	private WebElement noWishlistsFound;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Favorites']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Favorites'")
	private WebElement wishlistFavorites;

	public boolean isWishlistFavoritesDisplayed() {
		if (wishlistFavorites.isDisplayed()) {
			System.out.println(GREEN_CHECK + "Wishlist Favorites is displayed");
			return true;
		}

		else {
			System.out.println(RED_X + "Wishlist Favorites is not displayed");
			return false;
		}
	}

	public boolean addNewWishlistBtnEnabledIOS() {

		WebElement addNewWishlistBtn = driver.findElements(By.xpath("//XCUIElementTypeButton")).get(5);
		if (addNewWishlistBtn.isEnabled()) {
			System.out.println(GREEN_CHECK + " Add new wishlist button is enabled");
			return true;
		} else
			System.out.println(RED_X + "Add new wishlist button is missing");
		return false;
	}

	public boolean isShopSectionEmpty() {
		if (noWishlistsFound.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Shop section is empty");
			return true;
		} else
			System.out.println(GREEN_CHECK + " Shop section is not empty");
		return false;
	}

	public boolean addNewWishlistBtnEnabledAndroid() {

		WebElement addNewWishlistBtn = driver
				.findElement(By.xpath("//android.widget.Button[@index='1' and @class='android.widget.Button']"));
		if (addNewWishlistBtn.isEnabled()) {
			System.out.println(GREEN_CHECK + " Add new wishlist button is enabled");
			return true;
		} else
			System.out.println(RED_X + "Add new wishlist button is missing");
		return false;
	}

	public void addNewWishlistBtnEnabled() {

		if (driver instanceof IOSDriver) {
			addNewWishlistBtnEnabledIOS();

		} else {
			addNewWishlistBtnEnabledAndroid();
		}

	}

	public void clickShopTab() {
		shopTab.click();
	}

	public void clickRewardsTab() {
		rewardsTab.click();
	}

	public boolean noRewardsDisplayed() {
		List<WebElement> elements;
		if (driver instanceof IOSDriver) {
			elements = driver.findElements(AppiumBy.iOSNsPredicateString("label == '0 items'"));
		} else {
			elements = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='0 items']"));
			elements = elements.stream().filter(e -> "0 items".equals(e.getText())).collect(Collectors.toList());
		}
		if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
			System.out.println(GREEN_CHECK + " Rewards not added");
			return true;
		} else {
			System.out.println(GREEN_CHECK + " Rewards added");
			return false;
		}
	}

	public void getNumberOfRewards() {
		numberOfRewards.getText();
		System.out.println(GREEN_CHECK + "Number of rewards added: " + numberOfRewards.getText());
	}

	public String noSavedRewardsFoundTextDispalyed() {
		try {
			String titleText = noSavedRewardsFound.getText();
			if (titleText != null && !titleText.isEmpty()) {
				System.out.println(GREEN_CHECK
						+ " TEST PASSED: When rewards are not added the following text should be displayed - '"
						+ titleText + "'");
			} else {
				System.out.println(RED_X + " TEST FAILED: Text No saved rewards found not displayed");
			}
			return titleText;
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get text No saved rewards found - " + e.getMessage());
			return null;
		}
	}

	public String validateStoreInventoryTitle() {
		try {
			String titleText = viewStoreInventoryTitle.getText();
			if (titleText != null && !titleText.isEmpty()) {
				System.out.println(GREEN_CHECK
						+ " TEST PASSED: When Find items at store is ON, the following inventory title should be displayed - '"
						+ titleText + "'");
			} else {
				System.out.println(RED_X + " TEST FAILED: Inventory title not found");
			}
			return titleText;
		} catch (Exception e) {
			System.out.println(RED_X + " TEST FAILED: Could not get inventory title - " + e.getMessage());
			return null;
		}
	}

	public boolean isInStockSectionDisplayed() {
		if (inStock.isDisplayed()) {
			System.out.println(GREEN_CHECK + " In stock section is displayed when switch is ON");
			return true;
		}

		else {
			System.out.println(RED_X + " In stock section not found");
			return false;
		}
	}

	public boolean wishlistsTabsDisplayed() {
		if (shopTab.isDisplayed() && rewardsTab.isDisplayed()) {
			System.out.println(GREEN_CHECK + " The shop and rewards tabs are displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " The shop and rewards tabs are not displayed");
			return false;
		}
	}

	public boolean shareBtnEnabled() {
		if (shareBtn.isEnabled()) {
			System.out.println(GREEN_CHECK + " Share button is enabled");
			return true;
		}

		else {
			System.out.println(RED_X + " Share button is not enabled");
			return false;
		}
	}

	public boolean sendToStoreChatBtnEnabled() {
		if (sendToStoreChatBtn.isEnabled()) {
			System.out.println(GREEN_CHECK + " Send to store chat button is enabled");
			return true;
		}

		else {
			System.out.println(RED_X + " Send to store chat button is not enabled");
			return false;
		}
	}

	public boolean findItemsAtDisplayed() {
		if (findItemsAtStore.isDisplayed()) {
			System.out.println(
					GREEN_CHECK + " By the switch, the following text is displayed: " + findItemsAtStore.getText());
			return true;
		}

		else {
			System.out.println(RED_X + " Find items at is not displayed");
			return false;
		}
	}

	public WebElement getSwitchElement() {
		if (driver instanceof IOSDriver) {
			return driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSwitch"));
		} else {
			return driver.findElement(AppiumBy.id("com.zumiez.sandbox:id/local_store_switch"));
		}
	}

	public boolean isSwitchEnabled() {
		WebElement switchEl = getSwitchElement();
		if (driver instanceof IOSDriver) {
			String value = switchEl.getAttribute("value");
			return !"0".equals(value);
		} else {
			String checked = switchEl.getAttribute("checked");
			return "true".equalsIgnoreCase(checked);
		}
	}

	public void toggleSwitch(boolean shouldEnable) {
		WebElement switchEl = getSwitchElement();
		boolean currentState = isSwitchEnabled();
		if (currentState != shouldEnable) {
			switchEl.click();
			System.out.println(GREEN_CHECK + " Switch toggled to: " + shouldEnable);
		} else {
			System.out.println(GREEN_CHECK + " Switch already in desired state: " + shouldEnable);
		}
	}

	public void verifyWishlistsScreenButtons() {

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

	public boolean availablePointsTitleDisplayed() {
		try {
			System.out.println(GREEN_CHECK + "If reward is added the following title should be displayed:  "
					+ availablePointsTitle.getText());
			return availablePointsTitle.isDisplayed();
		} catch (Exception e) {
			System.out.println(RED_X + "Title Avaliable Points is not displayed when reward is added");
			return false;
		}
	}

	public boolean numberOfPointsDisplayed() {
		try {
			System.out.println(GREEN_CHECK + "If reward is added, number of points should be visble as expected:  "
					+ numberOfPoints.getText());
			return numberOfPoints.isDisplayed();
		} catch (Exception e) {
			System.out.println(RED_X + "Reward is added but number of points is missing");
			return false;
		}
	}

	public void getNumberOfPoints() {
		numberOfPoints.getText();
	}

	public boolean noSavedRewardsTitleDisplayed() {
		try {
			System.out.println(GREEN_CHECK + "If rewards are missing the following title should be displayed:  "
					+ noSavedRewardsFound.getText());
			return true;
		} catch (Exception e) {
			System.out.println(RED_X + "Title No saved rewards found is not displayed when rewards are missing");
			return false;
		}
	}

	public boolean isOutOfStockSectionDisplayed() {
		if (driver instanceof IOSDriver) {
			try {
				return outOfStock.isDisplayed();
			} catch (Exception e) {
				return false;
			}
		} else {
			List<WebElement> elements = driver
					.findElements(By.xpath("//android.widget.TextView[contains(@text, 'Out of stock')]"));
			if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
				return true;
			}
			try {
				String uiSelector = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"Out of stock\"))";
				WebElement scrolledElement = driver.findElement(MobileBy.AndroidUIAutomator(uiSelector));
				return scrolledElement.isDisplayed();
			} catch (Exception e) {
				return false;
			}
		}
	}
}
