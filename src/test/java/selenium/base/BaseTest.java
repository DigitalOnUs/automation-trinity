package selenium.base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base class for any test
 * 
 * @author Alberto Lopez
 *
 */
public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void initializeTest(Method method) throws MalformedURLException {
		
		// Initializes driver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod(alwaysRun = true)
	public void finalizeTest(ITestResult result, Method method) {

		// Quit driver
		driver.quit();
	}

	protected void goToPage(String url) {
		driver.get(url);
	}
}
