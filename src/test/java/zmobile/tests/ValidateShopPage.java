package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateShopPage extends MobileBaseTest {

	@Test(priority = 2)
	@ZephyrTest(key = "HTH-T146", description = "Validate shop screen elements")
	public void testShopScreenElements() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertEquals(shopPage.validateShopFullCollectionText(), "Shop the full collection");
		Assert.assertEquals(shopPage.validateShopTitle(), "Fresh picks");
	}

	@Test(priority = 1)
	@ZephyrTest(key = "HTH-T146", description = "Validate header on shop screen")
	public void validateHeaderOnShopScreen() throws InterruptedException {
		tabBarMenu.clickShopTab();
		// header.getSearchIcon();
		Assert.assertTrue(header.headerForShopScreen());

	}

	@Test(priority = 3)
	@ZephyrTest(key = "HTH-T146", description = "Validate bottom tab bar menu on shop screen")
	public void validateBottomTabBarMenuOnStashScreen() throws InterruptedException {
		Assert.assertTrue(tabBarMenu.isBottomTabBarMenuDisplayed());

	}

}
