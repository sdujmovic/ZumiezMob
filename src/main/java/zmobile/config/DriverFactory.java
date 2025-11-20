package zmobile.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

	public static AppiumDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(String platform, String environment) {
		// String environment = platform.toLowerCase() + "-" + buildType.toLowerCase();
		ConfigReader.initialize(environment);

		try {
			DesiredCapabilities caps = new DesiredCapabilities();

			String platformName = ConfigReader.getProperty("platform.name");
			caps.setCapability("platformName", platformName);
			caps.setCapability("automationName", ConfigReader.getProperty("automation.name"));
			caps.setCapability("deviceName", ConfigReader.getProperty("device.name"));
			caps.setCapability("platformVersion", ConfigReader.getProperty("platform.version"));
			caps.setCapability("app", ConfigReader.getProperty("app.path"));
			caps.setCapability("noReset", ConfigReader.getProperty("no.reset"));
			caps.setCapability("fullReset", ConfigReader.getProperty("full.reset"));

			if ("Android".equalsIgnoreCase(platformName)) {
				caps.setCapability("appPackage", ConfigReader.getProperty("app.package"));
				caps.setCapability("appActivity", ConfigReader.getProperty("app.activity"));
				driver.set(new AndroidDriver(new URL(ConfigReader.getAppiumServerUrl()), caps));
			} else if ("iOS".equalsIgnoreCase(platformName)) {
				caps.setCapability("bundleId", ConfigReader.getProperty("bundle.id"));
				caps.setCapability("udid", ConfigReader.getProperty("udid"));
				caps.setCapability("connectHardwareKeyboard", false); // CRITICAL for simulator
				caps.setCapability("unicodeKeyboard", true);
				caps.setCapability("resetKeyboard", true);
				driver.set(new IOSDriver(new URL(ConfigReader.getAppiumServerUrl()), caps));
			}

			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));

		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize driver", e);
		}
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
