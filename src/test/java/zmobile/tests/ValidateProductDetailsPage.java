package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import zmobile.pages.BasePage;
import zmobile.utils.ProductCatalog;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateProductDetailsPage extends MobileBaseTest {

	@Test
	@ZephyrTest(key = "HTH-T149", description = "Validate product details screen elements")
	public void testProductDetailsScreenElements() throws InterruptedException, IOException {

		ProductCatalog product = ProductCatalog.PRIMITIVE_HAT;

		String productName = product.getLabel();
		String productPrice = product.getPrice();

		Thread.sleep(5000);
		tabBarMenu.clickShopTab();
		shopPage.findProductByText(productName);
		productDetailsPage.validateProductImage();
		Thread.sleep(5000);
		productDetailsPage.validateProductTitle(productName);
		productDetailsPage.validateProductPrice(productPrice);
		Thread.sleep(5000);
		productDetailsPage.validateShareIcon();
		Assert.assertTrue(productDetailsPage.sizeDisplayed());
		profilePage.findElementByText("store chat");
		Assert.assertTrue(productDetailsPage.storeChatBtnDisplayed());
		Assert.assertTrue(productDetailsPage.isWishlistButtonDisplayed());
		String buttonText = productDetailsPage.getWishlistButtonText();
		System.out.println("Button text: " + buttonText);
		Assert.assertTrue(buttonText.contains("add") || buttonText.contains("remove"),
				"Button text doesn't contain expected values");
		productDetailsPage.keepScrollingDown();
		productDetailsPage.displayProductSectionsSummary();

		Thread.sleep(5000);
		profilePage.findElementByText("May We Suggest");
		basePage.hideKeyboard();
		Assert.assertTrue(productDetailsPage.mayWeSuggestTitleDisplayed());

	}
}
