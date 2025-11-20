package zmobile.base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import zmobile.config.ConfigReader;
import zmobile.config.DriverFactory;
import zmobile.utils.TestDataReader;

public class BaseTest {

	@Parameters({ "platform", "environment" })
	@BeforeClass(alwaysRun = true)
	public void setup(@Optional("ios") String platform, @Optional("ios-prod") String environment) {

		System.out.println("=== Test Setup Started ===");
		System.out.println("Platform: " + platform);
		System.out.println("Environment: " + environment);

		TestDataReader.resetBuildType();

		ConfigReader.initialize(environment);

		DriverFactory.setDriver(platform, environment);

		System.out.println("=== Test Setup Completed ===");
	}
}
