package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateHomePage extends MobileBaseTest {

	@Test(priority = 2)
	@ZephyrTest(key = "HTH-T144", description = "Validate home screen elements - home screen title, favorite store, lets talk button, keep exploring title and options")
	public void testHomeScreenElements() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue(homePage.homePageTitleDisplayed());
		Assert.assertTrue(homePage.favoriteStoreOrSetStoreEnabled());
		Assert.assertTrue(homePage.letsTalkBtnEnabled());
		homePage.scrollToHomeScreenBottom();
		Assert.assertTrue(homePage.isKeepExploringTitleDisplayed());
	}

	@Test(priority = 1)
	@ZephyrTest(key = "HTH-T144", description = "Validate header on home screen")
	public void validateHeaderOnHomeScreen() throws InterruptedException {
		Assert.assertTrue(header.headerForHomeScreen());

	}

	@Test(priority = 3)
	@ZephyrTest(key = "HTH-T144", description = "Validate bottom tab bar menu on home screen")
	public void validateBottomTabBarMenuOnHomeScreen() throws InterruptedException {
		Assert.assertTrue(tabBarMenu.isBottomTabBarMenuDisplayed());

	}

}
