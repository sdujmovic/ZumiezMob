package zmobile.config;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigReader {

	private static Properties properties;
	private static Properties envProperties;
	private static boolean initialized = false;

	public static void initialize(String environment) {
		if (initialized) {
			System.out.println("ConfigReader already initialized, skipping...");
			return;
		}

		properties = new Properties();
		envProperties = new Properties();

		try {
			String configPath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
			String envPath = System.getProperty("user.dir") + "/src/test/resources/" + environment + ".properties";

			System.out.println("Loading config from: " + configPath);
			System.out.println("Loading environment config from: " + envPath);

			properties.load(new FileInputStream(configPath));
			envProperties.load(new FileInputStream(envPath));

			initialized = true;
			System.out.println("ConfigReader initialized successfully for environment: " + environment);

		} catch (IOException e) {
			System.err.println("Failed to load configuration files");
			e.printStackTrace();
			throw new RuntimeException("Failed to load configuration files", e);
		}
	}

	public static String getProperty(String key) {
		if (!initialized) {
			throw new RuntimeException("ConfigReader not initialized. Call ConfigReader.initialize() first.");
		}

		String value = envProperties.getProperty(key);
		return value != null ? value : properties.getProperty(key);
	}

	public static String getAppiumServerUrl() {
		return getProperty("appium.server.url");
	}

	public static int getImplicitWait() {
		return Integer.parseInt(getProperty("implicit.wait"));
	}

	public static int getExplicitWait() {
		return Integer.parseInt(getProperty("explicit.wait"));
	}

	public static boolean isInitialized() {
		return initialized;
	}

}
