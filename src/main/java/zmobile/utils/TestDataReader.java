package zmobile.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import zmobile.config.ConfigReader;
import java.io.FileReader;
import java.io.IOException;

public class TestDataReader {

	private static JsonObject testData;
	private static String currentBuildType = null;

	static {
		try {
			String dataPath = System.getProperty("user.dir") + "/src/test/resources/testdata/login-data.json";
			testData = JsonParser.parseReader(new FileReader(dataPath)).getAsJsonObject();
			System.out.println("TestDataReader: login-data.json loaded successfully");
		} catch (IOException e) {
			throw new RuntimeException("Failed to load test data from login-data.json", e);
		}
	}

	public static String detectBuildType() {
		try {
			String appPath = ConfigReader.getProperty("app.path");

			String buildType;
			if (appPath != null) {
				String upperPath = appPath.toUpperCase();
				if (upperPath.contains("PROD") || upperPath.contains("/PROD/")) {
					buildType = "prod";
				} else if (upperPath.contains("DEBUG") || upperPath.contains("/DEBUG/")) {
					buildType = "debug";
				} else {
					if (upperPath.contains("DEBUG")) {
						buildType = "debug";
					} else {
						buildType = "debug";
					}
				}
			} else {
				buildType = "debug";
			}

			System.out.println("TestDataReader: Build type detected as '" + buildType + "' from app path: " + appPath);
			currentBuildType = buildType;
			return buildType;

		} catch (Exception e) {
			System.err.println(
					"TestDataReader: Could not detect build type, defaulting to 'debug'. Error: " + e.getMessage());
			currentBuildType = "debug";
			return "debug";
		}
	}

	public static String getValidEmail() {
		String buildType = detectBuildType();
		String email = testData.getAsJsonObject("validCredentials").getAsJsonObject(buildType).get("email")
				.getAsString();
		System.out.println("TestDataReader: Using email for '" + buildType + "': " + email);
		return email;
	}

	public static String getValidPhone() {
		String buildType = detectBuildType();
		String phone = testData.getAsJsonObject("validCredentials").getAsJsonObject(buildType).get("phone")
				.getAsString();
		System.out.println("TestDataReader: Using phone for '" + buildType + "': " + phone);
		return phone;
	}

	public static String getValidPassword() {
		String buildType = detectBuildType();
		return testData.getAsJsonObject("validCredentials").getAsJsonObject(buildType).get("password").getAsString();
	}

	public static String getNotRegisteredEmail() {
		return testData.getAsJsonObject("notRegisteredUser").get("email").getAsString();
	}

	public static String getNotRegisteredPhone() {
		return testData.getAsJsonObject("notRegisteredUser").get("phone").getAsString();
	}

	public static String getNotRegisteredPassword() {
		return testData.getAsJsonObject("notRegisteredUser").get("password").getAsString();
	}

	public static String getInvalidCredential(String type) {
		return testData.getAsJsonObject("invalidCredentials").get(type).getAsString();
	}

	public static String getErrorMessage(String type) {
		return testData.getAsJsonObject("errorMessages").get(type).getAsString();
	}

	public static String getCurrentBuildType() {
		if (currentBuildType == null) {
			detectBuildType();
		}
		return currentBuildType;
	}

	public static void resetBuildType() {
		System.out.println("TestDataReader: Build type cache reset");
		currentBuildType = null;
	}

	public static String getNotRegisteredUser(String type) {
		return testData.getAsJsonObject("notRegisteredUser").get(type).getAsString();
	}
}
