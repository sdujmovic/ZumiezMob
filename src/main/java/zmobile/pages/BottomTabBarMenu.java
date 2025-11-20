package zmobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import static zmobile.utils.ConsoleColors.GREEN_CHECK;
import static zmobile.utils.ConsoleColors.RED_X;

public class BottomTabBarMenu extends BasePage {

	public BottomTabBarMenu(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='header_profile_image']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'more'")
	private WebElement moreTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='home']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'home'")
	private WebElement homeTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='stash']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'stash'")
	private WebElement stashTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='shop']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'shop'")
	private WebElement shopTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='wishlists']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'wishlists'")
	private WebElement wishlistsTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='bag']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'bag'")
	private WebElement bagTab;

	public void clickHomeTab() {
		homeTab.click();
	}

	public void clickStashTab() {
		stashTab.click();
	}

	public void clickShopTab() {
		shopTab.click();
	}

	public void clickWishlistsTab() {
		wishlistsTab.click();
	}

	public void clickMoreTab() {
		moreTab.click();
	}

	public void clickBagTab() {
		bagTab.click();
	}

	public boolean isBottomTabBarMenuDisplayed() {
		if (homeTab.isDisplayed() && stashTab.isDisplayed() && shopTab.isDisplayed() && wishlistsTab.isDisplayed()
				&& bagTab.isDisplayed()) {
			System.out.println(GREEN_CHECK
					+ " The following bottom tab bars are displayed on Home screen: home, stash, shop, wishlists, bag");
			return true;
		} else {
			System.out.println(RED_X
					+ " The following bottom tab bars are NOT displayed on Home screen: home, stash, shop, wishlists, bag");
			return false;
		}
	}
}
