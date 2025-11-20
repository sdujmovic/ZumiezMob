package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateBagPage extends MobileBaseTest {

	@Test(priority = 1)
	@ZephyrTest(key = "HTH-T152", description = "Validate bag screen elements")
	public void testBagScreenElements() throws InterruptedException {
		tabBarMenu.clickBagTab();
		Thread.sleep(5000);
		Assert.assertTrue(header.isMyBagTitleDisplayed());
		Assert.assertTrue(bagPage.bagTabsDisplayed(), "Bag and Saved for later tabs should be displayed.");

	}

	@Test(priority = 4)
	@ZephyrTest(key = "HTH-T152", description = "Validate bottom tab bar menu on bag screen")
	public void validateBottomTabBarMenuOnBagScreen() throws InterruptedException {
		Assert.assertTrue(tabBarMenu.isBottomTabBarMenuDisplayed());
	}

	@Test(priority = 2)
	@ZephyrTest(key = "HTH-T152", description = "Validate elements on bag tab screen")
	public void testBagTabScreenElements() throws InterruptedException {
		bagPage.clickBagTab();
		Thread.sleep(2000);
		if (bagPage.noItemsInBag()) {
			Assert.assertTrue(bagPage.noItemsInBag());
		} else {
			Thread.sleep(2000);
			Assert.assertTrue(bagPage.itemDetailsDisplayed());
			Assert.assertTrue(bagPage.deliveryOptionsDisplayed());
			Assert.assertTrue(bagPage.orderTotalDisplayed());
			Assert.assertTrue(bagPage.proceedToCheckoutBtn());
		}
	}

	@Test(priority = 3)
	@ZephyrTest(key = "HTH-T152", description = "Validate elements on save for later tab screen")
	public void testSavedForLaterTabScreenElements() throws InterruptedException {
		bagPage.savedForLaterTab();
		Thread.sleep(2000);

		if (bagPage.noItemsSavedForLater()) {
			Assert.assertTrue(bagPage.noItemsSavedForLater());
		} else {
			Thread.sleep(2000);
			bagPage.getNumberOfItemsSavedForLater();
		}
	}

}
