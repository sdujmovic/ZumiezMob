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

public class BagPage extends BasePage {

	public BagPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='BAG']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'BAG'")
	private WebElement bagTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SAVED FOR LATER']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'SAVED FOR LATER'")
	private WebElement savedForLaterTab;

	@AndroidFindBy(xpath = "//android.view.View[contains(@text, 'item')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'item'")
	private WebElement numberOfItemsSavedForLater;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No Items in bag']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'No Items in bag'")
	private WebElement noItemsInBag;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Size:']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Size'")
	private WebElement itemSize;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Decrement']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Quantity'")
	private WebElement itemQuantity;

	@AndroidFindBy(xpath = "//android.view.View[contains(@text, '$')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS '$'")
	private WebElement itemPrice;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ship to me']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Ship to me'")
	private WebElement shipToMe;

	// @AndroidFindBy(xpath = "//android.widget.TextView[@text='free in-store pick
	// up']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Free in-store pickup'")
	private WebElement inStorePickup;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Order total']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Order total'")
	private WebElement orderTotal;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PROCEED TO CHECKOUT']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'PROCEED TO CHECKOUT'")
	private WebElement proceedToCheckoutBtn;

	public void clickBagTab() {
		bagTab.click();
	}

	public void savedForLaterTab() {
		savedForLaterTab.click();
	}

	public boolean itemDetailsDisplayed() {
		if (itemSize.isDisplayed() && itemQuantity.isDisplayed() && itemPrice.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Item details including item size, quantity and price are displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " Item details are missing");
			return false;
		}
	}

	public boolean deliveryOptionsDisplayed() {
		if (shipToMe.isDisplayed() && inStorePickup.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Delivery options - ship to me and in store pickup are displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " Item details are missing");
			return false;
		}
	}

	public boolean orderTotalDisplayed() {
		if (orderTotal.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Order total is displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " Order total is missing missing");
			return false;
		}
	}

	public boolean proceedToCheckoutBtn() {
		if (proceedToCheckoutBtn.isDisplayed()) {
			System.out.println(GREEN_CHECK + " Proceed to checkout button is displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " Proceed to checkout button is missing");
			return false;
		}
	}

	public boolean bagTabsDisplayed() {
		if (bagTab.isDisplayed() && savedForLaterTab.isDisplayed()) {
			System.out.println(GREEN_CHECK + " The bag and saved for later tabs are displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " The bag and saved for later tabs are not displayed");
			return false;
		}
	}

	public boolean noItemsSavedForLater() {
		List<WebElement> elements;
		if (driver instanceof IOSDriver) {
			elements = driver.findElements(AppiumBy.iOSNsPredicateString("label == 'No Items Saved for Later'"));
		} else {
			elements = driver
					.findElements(AppiumBy.xpath("//android.widget.TextView[@text='No Items in Saved for Later']"));
			elements = elements.stream().filter(e -> "0 items".equals(e.getText())).collect(Collectors.toList());
		}
		if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
			System.out.println(GREEN_CHECK + " Items not saved for later");
			return true;
		} else {
			System.out.println(GREEN_CHECK + " Items are saved for later");
			return false;
		}
	}

	public boolean noItemsInBag() {
		List<WebElement> elements;
		if (driver instanceof IOSDriver) {
			elements = driver.findElements(AppiumBy.iOSNsPredicateString("label == 'No Items in bag'"));
		} else {
			elements = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='No Items in bag']"));
			elements = elements.stream().filter(e -> "0 items".equals(e.getText())).collect(Collectors.toList());
		}
		if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
			System.out.println(GREEN_CHECK + " There are no items in bag");
			return true;
		} else {
			System.out.println(GREEN_CHECK + " Items are in bag");
			return false;
		}
	}

	public void getNumberOfItemsSavedForLater() {
		numberOfItemsSavedForLater.getText();
		System.out.println(GREEN_CHECK + "Number of items saved for later: " + numberOfItemsSavedForLater.getText());
	}

}
