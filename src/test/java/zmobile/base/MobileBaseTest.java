package zmobile.base;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.AppiumDriver;
import zmobile.config.DriverFactory;
import zmobile.pages.HomePage;
import zmobile.pages.LandingPage;
import zmobile.pages.LoginPage;
import zmobile.pages.ProductDetailsPage;
import zmobile.pages.BagPage;
import zmobile.pages.BasePage;
import zmobile.pages.BottomTabBarMenu;
import zmobile.pages.EditProfilePage;
import zmobile.pages.Header;
import zmobile.pages.ProfilePage;
import zmobile.pages.SearchPage;
import zmobile.pages.ShopPage;
import zmobile.pages.StashPage;
import zmobile.pages.WishlistsPage;
import zmobile.utils.TestDataReader;

public class MobileBaseTest extends BaseTest {

	protected LandingPage landingPage;
	protected LoginPage loginPage;
	protected Header header;
	protected HomePage homePage;
	protected ProfilePage profilePage;
	protected BottomTabBarMenu tabBarMenu;
	protected StashPage stashPage;
	protected ShopPage shopPage;
	protected EditProfilePage editProfilePage;
	protected ProductDetailsPage productDetailsPage;
	protected SearchPage searchPage;
	protected WishlistsPage wishlistsPage;
	protected BagPage bagPage;
	protected BasePage basePage;

	public static final String GREEN_CHECK = "✅";
	public static final String RED_X = "❌";
	public static final String YELLOW_WARNING = "⚠️";

	@BeforeMethod
	public void initializePages() throws InterruptedException {
		System.out.println("Setting up login test - initializing all pages");
		AppiumDriver driver = DriverFactory.getDriver();

		landingPage = new LandingPage(driver);
		loginPage = new LoginPage(driver);
		header = new Header(driver);
		homePage = new HomePage(driver);
		profilePage = new ProfilePage(driver);
		tabBarMenu = new BottomTabBarMenu(driver);
		stashPage = new StashPage(driver);
		shopPage = new ShopPage(driver);
		editProfilePage = new EditProfilePage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		searchPage = new SearchPage(driver);
		wishlistsPage = new WishlistsPage(driver);
		bagPage = new BagPage(driver);
		basePage = new BasePage(driver);

		navigateToLoginScreen();

		System.out.println("All pages initialized successfully");
		Thread.sleep(500);
	}

	private void navigateToLoginScreen() throws InterruptedException {
		System.out.println("Navigating to login screen...");

		try {
			if (loginPage.isOnLoginScreen()) {
				System.out.println("Already on login screen, skipping navigation");
				return;
			}
			System.out.println("Not on login screen, attempting to navigate...");
			try {
				landingPage.goToCreateAccountPage();
				Thread.sleep(1000);
				System.out.println("Navigated to create account page");
			} catch (Exception e) {
				System.out.println("Could not click GET STARTED button: " + e.getMessage());
				System.out.println("App might already be at login screen or in different state");
				dismissAnyOverlays();
				Thread.sleep(1000);

				if (loginPage.isOnLoginScreen()) {
					System.out.println("Successfully reached login screen after dismissing overlays");
					return;
				}

				System.err.println("WARNING: Could not navigate to login screen. App state might be unexpected.");
			}

			System.out.println("Navigation complete");

		} catch (Exception e) {
			System.err.println("Error during navigation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void dismissAnyOverlays() {
		try {
			AppiumDriver driver = DriverFactory.getDriver();

			boolean isAndroid = isAndroid();
			boolean isIOS = isIOS();

			if (isAndroid) {
				System.out.println("Attempting to dismiss Android overlays...");
				try {
					((AndroidDriver) driver).hideKeyboard();
					Thread.sleep(500);
					System.out.println("Hid keyboard");
				} catch (Exception e) {
					System.out.println("Could not hide keyboard: " + e.getMessage());
				}
			} else if (isIOS) {
				System.out.println("Attempting to dismiss iOS overlays...");
				try {
					((IOSDriver) driver).hideKeyboard();
					Thread.sleep(500);
					System.out.println("Hid keyboard on iOS");
				} catch (Exception e) {
					System.out.println("Could not hide keyboard: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error dismissing overlays: " + e.getMessage());
		}
	}

	protected boolean isAndroid() {
		try {
			return DriverFactory.getDriver().getCapabilities().getPlatformName().toString().equalsIgnoreCase("ANDROID");
		} catch (Exception e) {
			return false;
		}
	}

	protected boolean isIOS() {
		try {
			return DriverFactory.getDriver().getCapabilities().getPlatformName().toString().equalsIgnoreCase("IOS");
		} catch (Exception e) {
			return false;
		}
	}

	@Test
	public void launchApp() throws Exception {

		String email = TestDataReader.getValidEmail();
		String password = TestDataReader.getValidPassword();

		System.out.println("Starting test: Login with valid existing user by email");
		System.out.println("Build type: " + TestDataReader.getCurrentBuildType());
		System.out.println("Testing with email: " + email);

		loginPage.enterLoginInfo(email);
		Thread.sleep(500);

		loginPage.clickContinueToLogin();
		Thread.sleep(1000);

		loginPage.enterPassword(password);
		Thread.sleep(500);

		loginPage.clickContinueToLogin();
		Thread.sleep(2000);
		loginPage.allowNotificationsIfNeeded();

		Assert.assertTrue(loginPage.isLoginSuccessful(), "User should be logged in successfully");

		Thread.sleep(2000);
		System.out.println("Login successful with email " + TestDataReader.getCurrentBuildType() + " credentials");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
