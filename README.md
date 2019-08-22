# Test Automation Trinity
## Automation Training for API / UI / Mobile

### Training Prerequistes

- IDE (Preferred Eclipse with Egit / Maven / TestNG plugins)
- Java SDK 1.8
- Maven 3.3 or newer
- Appium Standalone & Desktop
- Emulator for Android device or Android studio

NOTE: A separate document guide will be created in this same repository for Appium/Android related requirements.

### To execute this project:

##### API
> mvn clean test -Dxml.suite=restassured_test.xml

This project is quite straightforward, and validates some responses from `https://jsonplaceholder.typicode.com`

**NOTE:** Internet connection is required to execute this project.

##### Web UI (Chrome)
> mvn clean test -Dxml.suite=selenium_test.xml

It should exist a system property `webdriver.chrome.driver` pointing to an executable driver for Chrome (chromedriver).

**NOTE:** Internet connection is required to execute this project.

##### Mobile (Android)
> mvn clean test -Dxml.suite=appium_test.xml

Within appium_test.xml, one can find all desired capabilities to setup the device.

This project runs on emulator referenced in appium_test.xml file on `clientCapabilities.deviceName` property.

There is a basic Android application at: `src/test/resources/apps/app-debug.apk`

**NOTE:** Emulator needs to be running prior executing this project. No Appium server needs to be running, as project will open one by itself.
