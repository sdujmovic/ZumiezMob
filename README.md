# Android Automated Testing for Zumiez Mobile App
Test script is created to run tests that verify basic functionality works. These should run quickly and catch major breaks early in the development cycle. Test script is written to run tests on Android integrated with Extent Reports that present the test results after run. Test status is automaticaly updated in Zephyr. Tests can be run locally and on Browserstack. Test script will be soon updated for running tests on iOS.
To run java based Appium tests for Android in Eclipse, you will need to set up several components and configure your environment properly. 

## Prerequisites
### Java Development Kit (JDK)
- Install JDK 8 or higher
- Set JAVA_HOME environment variable
### Android SDK
- Download Android Studio or standalone SDK tools
- Set ANDROID_HOME environment variable pointing to SDK location
- Add SDK platform-tools and tools to your PATH
### Node.js and Appium
- Install Node.js (version 14 or higher recommended)
- Install Appium server: ```npm install -g appium```
- Install Appium drivers: ```appium driver install uiautomator2```
### Eclipse IDE Setup
- Install Eclipse IDE for Java Developers
- Install TestNG plugin for using TestNG framework
- Install Maven integration plugin (usually pre-installed)
### Project Dependencies
Create a Maven project and add these dependencies to pom.xml:
```xml
<dependencies>
    <dependency>
        <groupId>io.appium</groupId>
        <artifactId>java-client</artifactId>
        <version>8.6.0</version>
    </dependency>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.15.0</version>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.8.0</version>
    </dependency>
</dependencies>
```
## Android Device/Emulator Setup
### Physical Device
- Enable Developer Options and USB Debugging
- Install appropriate USB drivers
- Verify connection with adb devices
### Android Emulator
- Create AVD (Android Virtual Device) through Android Studio
- Ensure emulator is running before test execution
## Environment Variables 
### Windows:
JAVA_HOME=C:\Program Files\Java\jdk-11.0.x
ANDROID_HOME=C:\Users\YourName\AppData\Local\Android\Sdk
PATH=%PATH%;%ANDROID_HOME%\platform-tools;%ANDROID_HOME%\tools
### Mac:
export ANDROID_HOME=~/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools
export JAVA_HOME=$(/usr/libexec/java_home)
export PATH=$PATH::$JAVA_HOME/bin
### Before Test Execution
- Check if you added apk file in directory: src/main/test/java/zmobile/resources
- Update path for apk file in AndroidBaseTest class
- Update data.properties file with your data
### Run Tests
- Start Appium server: appium (from command line)
- Ensure Android device/emulator is connected and running
- Configure desired capabilities in your test script
- Run test from Eclipse (right-click test file → Run As → TestNG Test)
## Extent Reports
The Extent Reports provide rich HTML reporting with charts, graphs, and detailed test execution
information perfect for stakeholders and CI/CD integration. Add this dependency to pom.xml:
```xml
<dependency> 
    <groupId>com.aventstack</groupId> 
    <artifactId>extentreports</artifactId> 
    <version>5.0.9</version> 
</dependency>
```
### Automatic Report Generation
- HTML reports with timestamps
- Detailed test execution logs
### Test Status Logging
- INFO, PASS, FAIL, SKIP status logging
- Step-by-step test execution details
- System information in reports

Run directly from Eclipse by right-clicking on testng.xml → Run As → TestNG Suite. The report will be generated in the test-output/ directory as ExtentReport.html.
## Integration with Zephyr
This integration ensures that automated test results are automatically reflected in Zephyr, providing real-time test execution status to QA team and stakeholders.
### Steps
- Create test cycle in Zephyr under which execution will be recorded
- Use key as testCycleKey in test execution
- Add a valid testCycleKey in payload
- Define it in data.properties (located at src/test/resources/zmobile/data.properties)
- Create Zephyr api token and add it in data.properties
- Add project key in data.properties
- Create ZephyrScaleUploader class 
- Call ZephyrScaleUploader.uploadResult(“testCaseKey", true);
### Key Features
- PASS/FAIL/SKIP status automatically sent to Zephyr
- Comments with failure reasons included
- Multiple mapping strategies (annotation or utility class)
- Environment-specific configurations
## Running Tests on BrowserStack
Add these dependencies to pom.xml:
```xml
<dependency>
    <groupId>com.browserstack</groupId>
    <artifactId>browserstack-local-java</artifactId>
    <version>1.0.6</version>
</dependency>
		
<dependency>
    <groupId>com.browserstack</groupId>
    <artifactId>browserstack-java-sdk</artifactId>
    <version>LATEST</version>
</dependency>
```
### Prerequisites
- Make sure to have BrowserStack account
- Username and access key
- Test already works locally
- Update AndroidBaseTest class with BrowserStack capabilities
- Upload app to BrowserStack
  ```   curl -u "USERNAME:ACCESS_KEY" \
        -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
        -F “file=@/path/to/your-app.apk"
  ```
  You will get back response like:
  ```
  	{
          "app_url": "bs://<app-id>"
        }
   ```
    Use that bs:// URL in caps.setCapability("app", ...) line
- In data.properties, define BrowserStack username, key and app
- In data.properties, define platform: local or browserstack
- In data.properties, define buildType: debug or production
### Run the Test
Run test like any other Java test. Log in to BrowserStack → Navigate to App Automate → You’ll see your test results, logs, video, and device logs.

Combining Extent Reports, Zephyr Scale integration, and the ability to run tests locally and on BrowserStack creates a powerful and scalable mobile test automation framework.
Extent Reports offer rich, visual test reporting for both developers and QA, enhancing debugging and analysis. Zephyr integration ensures full traceability and real-time visibility within Jira, with automated test status updates. The ability to execute tests locally and on BrowserStack provides the flexibility to debug efficiently and scale testing across real devices. Together, these elements form a robust, professional-grade automation framework that delivers clear quality signals across all levels of the team.






