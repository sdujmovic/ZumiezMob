package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateSearchPage extends MobileBaseTest {

	@Test(priority = 1)
	@ZephyrTest(key = "HTH-T150", description = "Validate search screen elements")
	public void testSearchScreenElements() throws InterruptedException {
		Thread.sleep(5000);
		header.selectSearchIcon();
		searchPage.validateLeftBackBtn();
		Assert.assertTrue(searchPage.searchInputFieldDisplayed());
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	@ZephyrTest(key = "HTH-T150", description = "Validate search with valid input")
	public void testSearchValidInput() throws InterruptedException {
		String searchTerm = "dress";
		searchPage.itemSearch(searchTerm);
		Assert.assertTrue(searchPage.verifySearchResults(searchTerm));
		Thread.sleep(5000);
		searchPage.numberOfItemsFound();

	}
}
