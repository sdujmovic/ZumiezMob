package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateStashPage extends MobileBaseTest {

	@Test(priority = 2)
	@ZephyrTest(key = "HTH-T145", description = "Validate stash screen elements - stash tabs, titles and if filter and sort tabs are displayed")
	public void testStashScreenElements() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue(stashPage.stashTabsDisplayed());
		Assert.assertTrue(stashPage.validateElementsForStashRewardsScreen());
		Assert.assertTrue(stashPage.validateElementsForStashEarnsScreen());
	}

	@Test(priority = 1)
	@ZephyrTest(key = "HTH-T145", description = "Validate header on stash screen")
	public void validateHeaderOnStashScreen() throws InterruptedException {
		tabBarMenu.clickStashTab();
		Assert.assertTrue(header.headerForStashScreen());

	}

	@Test(priority = 3)
	@ZephyrTest(key = "HTH-T145", description = "Validate bottom tab bar menu on stash screen")
	public void validateBottomTabBarMenuOnStashScreen() throws InterruptedException {
		Assert.assertTrue(tabBarMenu.isBottomTabBarMenuDisplayed());

	}

}
