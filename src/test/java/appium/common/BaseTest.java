package appium.common;

import io.appium.java_client.AppiumDriver;
import appium.AppiumDriverFactory;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class BaseTest {

	public AppiumDriver driver;
	public final static Logger logger = Logger.getLogger(BaseTest.class);
	private AppiumDriverFactory driverCreator = new AppiumDriverFactory();

	@BeforeClass(alwaysRun = true)
	public void setupDriver(ITestContext context) {
		try {
			driver = driverCreator.createDriver(context);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		driverCreator.closeServer();
	}
}
