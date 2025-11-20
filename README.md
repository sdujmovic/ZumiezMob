# Automated Testing for Zumiez Mobile App
Test script is created to run tests that verify basic functionality works. These should run quickly and catch major breaks early in the development cycle. Test script is written for cross device testing on iOS and Android. Test status is automaticaly updated in Zephyr. Tests can be run locally and on Browserstack. To run java based Appium tests in Eclipse, you will need to set up several components and configure your environment properly. 

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
## Device/Emulator Setup
### Physical Device
- Enable Developer Options and USB Debugging
- Install appropriate USB drivers
- Verify connection with adb devices
### Android Emulator
- Create AVD (Android Virtual Device) through Android Studio
- Ensure emulator is running before test execution
### iOS Simulator
- Download the XCode IDE
- Start up an iOS simulator using XCode
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
- Check if you added .apk and .app files in directory: src/main/test/java/zmobile/resources/versions
- Update path for .apk and .app files in properties files
- Update login-data.json file with your data
### Run Tests
- Start Appium server: appium (from command line)
- Ensure Android device/emulator is connected and running
- Ensure iOS device/emulator is connected and running
- Configure desired capabilities in your test script
- Run test from Eclipse (right-click test file → Run As → TestNG Test)
### Automatic Report Generation
- HTML reports with timestamps
- Detailed test execution logs
### Test Status Logging
- INFO, PASS, FAIL, SKIP status logging
- Step-by-step test execution details
- System information in reports

Run directly from Eclipse by right-clicking on testng.xml → Run As → TestNG Suite. The report will be generated in the test-output/ directory.
## Integration with Zephyr
This integration ensures that automated test results are automatically reflected in Zephyr, providing real-time test execution status to QA team and stakeholders.
### Steps
- Create test cycle in Zephyr under which execution will be recorded
- Use key as testCycleKey in test execution
- Add a valid testCycleKey in payload
- Define it in data.properties (located at src/test/resources/config.properties)
- Create Zephyr api token and add it in config.properties
- Add project key in config.properties
- Create ZephyrScaleUploader class 
- Call ZephyrScaleUploader.uploadResult(“testCaseKey", true);
### Key Features
- PASS/FAIL/SKIP status automatically sent to Zephyr
- Comments with failure reasons included
- Multiple mapping strategies (annotation or utility class)
- Environment-specific configurations
  
### Run the Test
Run test like any other Java test. Test script can be integrated with BrowserStack. In that case, config.properties file should hold browserstack credentials.
Combining Zephyr Scale integration, and the ability to run tests locally and on BrowserStack creates a powerful and scalable mobile test automation framework. Zephyr integration ensures full traceability and real-time visibility within Jira, with automated test status updates. The ability to execute tests locally and on BrowserStack provides the flexibility to debug efficiently and scale testing across real devices. Together, these elements form a robust, professional-grade automation framework that delivers clear quality signals across all levels of the team.






