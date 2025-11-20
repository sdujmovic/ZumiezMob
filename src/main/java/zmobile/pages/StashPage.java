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

public class StashPage extends BasePage {

	public StashPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "stash_reward_tab")
	@iOSXCUITFindBy(accessibility = "stash_reward_tab")
	private WebElement stashRewardTab;

	@AndroidFindBy(accessibility = "stash_earn_tab")
	@iOSXCUITFindBy(accessibility = "stash_earn_tab")
	private WebElement stashEarnTab;

	@AndroidFindBy(accessibility = "stash_reward_header")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Rewards to aim for'")
	private WebElement rewardHeader;

	@AndroidFindBy(accessibility = "stash_reward_header")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'More ways to earn'")
	private WebElement earnHeader;

	@AndroidFindBy(id = "com.zumiez.sandbox:id/filterButton")
	@AndroidFindBy(id = "com.zumiez:id/filterButton")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'FILTER'")
	private WebElement filterTitle;

	@AndroidFindBy(id = "com.zumiez.sandbox:id/sortButton")
	@AndroidFindBy(id = "com.zumiez:id/sortButton")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'SORT'")
	private WebElement sortTitle;

	public boolean stashTabsDisplayed() {
		if (stashRewardTab.isDisplayed() && stashEarnTab.isDisplayed()) {
			System.out.println(GREEN_CHECK + " The reward and earn tabs are displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " The reward and earn tabs are not displayed");
			return false;
		}
	}

	public boolean validateElementsForStashRewardsScreen() {
		stashRewardTab.click();
		System.out.println("Getting to Rewards screen");
		if (rewardHeaderDisplayed() && filterTabDisplayed() && sortTabDisplayed()) {
			System.out.println(GREEN_CHECK
					+ " All elements on reward screen including reward header, filter and sort tabs are displayed as expected");
			return true;
		}

		else {
			System.out.println(RED_X + "Elements on reward screen are not displayed as expected");
			return false;
		}

	}

	public boolean validateElementsForStashEarnsScreen() {
		stashEarnTab.click();
		System.out.println("Getting to Earns screen");
		if (earnHeaderDisplayed() && filterTabDisplayed() && sortTabDisplayed()) {
			System.out.println(GREEN_CHECK
					+ " All elements on earn screen including earn header, filter and sort tabs are displayed as expected");
			return true;
		}

		else {
			System.out.println(RED_X + "Elements on earn screen are not displayed as expected");
			return false;
		}

	}

	public boolean rewardHeaderDisplayed() {
		stashRewardTab.click();
		System.out.println("Getting to Rewards screen");
		if (rewardHeader.isDisplayed()) {
			System.out.println(GREEN_CHECK + " The reward header is displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " The reward header is not displayed");
			return false;
		}

	}

	public boolean earnHeaderDisplayed() {
		stashEarnTab.click();
		System.out.println("Getting to Earns screen");
		if (earnHeader.isDisplayed()) {
			System.out.println(GREEN_CHECK + " The earn header is displayed");
			return true;
		}

		else {
			System.out.println(RED_X + " The earn header is not displayed");
			return false;
		}

	}

	public boolean filterTabDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(ExpectedConditions
						.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == 'FILTER'")));
				if (element != null && element.isEnabled()) {
					System.out.println("✓ Filter tab found");
					return true;
				} else {
					System.out.println("✗ Filter tab not enabled");
					return false;
				}

			} else {
				String id = getFilterTabId();
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id(id)));
				System.out.println("✓ Filter tab found " + element.getText());
				return element.isEnabled();
			}
		} catch (Exception e) {
			System.out.println("✗ Filter tab not enabled " + e.getMessage());
			return false;
		}
	}

	private String getFilterTabId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/filterButton";
		case "prod":
		case "production":
			return "com.zumiez:id/filterButton";
		default:
			return "com.zumiez.sandbox:id/filterButton";
		}
	}

	public boolean sortTabDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (driver instanceof IOSDriver) {
				WebElement element = wait.until(
						ExpectedConditions.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("label == 'SORT'")));
				if (element != null && element.isEnabled()) {
					System.out.println("✓ Sort tab found");
					return true;
				} else {
					System.out.println("✗ Sort tab not enabled");
					return false;
				}

			} else {
				String id = getSortTabId();
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id(id)));
				System.out.println("✓ Sort tab found " + element.getText());
				return element.isEnabled();
			}
		} catch (Exception e) {
			System.out.println("✗ Sort tab not enabled " + e.getMessage());
			return false;
		}
	}

	private String getSortTabId() {
		String buildType = TestDataReader.detectBuildType();

		switch (buildType.toLowerCase()) {
		case "debug":
		case "sandbox":
			return "com.zumiez.sandbox:id/sortButton";
		case "prod":
		case "production":
			return "com.zumiez:id/sortButton";
		default:
			return "com.zumiez.sandbox:id/sortButton";
		}
	}
}
