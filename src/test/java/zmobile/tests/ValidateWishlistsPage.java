package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateWishlistsPage extends MobileBaseTest {

	@Test(priority = 1)
	@ZephyrTest(key = "HTH-T151", description = "Validate wishlists screen elements")
	public void testWishlistsScreenElements() throws InterruptedException {
		tabBarMenu.clickWishlistsTab();
		Thread.sleep(5000);
		Assert.assertTrue(header.isWishlistTitleDisplayed());
		Assert.assertTrue(wishlistsPage.wishlistsTabsDisplayed(), "Shop and Rewards tabs should be displayed.");
	}

	@Test(priority = 4)
	@ZephyrTest(key = "HTH-T151", description = "Validate bottom tab bar menu on wishlists screen")
	public void validateBottomTabBarMenuOnWishlistsScreen() throws InterruptedException {
		Assert.assertTrue(tabBarMenu.isBottomTabBarMenuDisplayed());

	}

	@Test(priority = 2)
	@ZephyrTest(key = "HTH-T151", description = "Validate elements on wishlists shop screen")
	public void testWishlistsShopScreenElements() throws InterruptedException {
		wishlistsPage.clickShopTab();
		Thread.sleep(2000);
		Assert.assertTrue(wishlistsPage.isWishlistFavoritesDisplayed());
		wishlistsPage.addNewWishlistBtnEnabled();
	}

	@Test(priority = 3)
	@ZephyrTest(key = "HTH-T151", description = "Validate elements on wishlists shop screen")
	public void testWishlistsRewardsScreenElements() throws InterruptedException {
		wishlistsPage.clickRewardsTab();
		Thread.sleep(2000);

		if (wishlistsPage.noRewardsDisplayed()) {
			Assert.assertTrue(wishlistsPage.noSavedRewardsTitleDisplayed());
			wishlistsPage.getNumberOfRewards();

		} else {
			Thread.sleep(2000);
			wishlistsPage.getNumberOfPoints();
			Assert.assertTrue(wishlistsPage.availablePointsTitleDisplayed());
			wishlistsPage.getNumberOfRewards();
		}
	}

}
