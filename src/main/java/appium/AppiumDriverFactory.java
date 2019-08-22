package appium;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumDriverFactory {

	private final static Logger logger = Logger.getLogger(AppiumDriverFactory.class);
	
	private static AppiumServer appiumServer = new AppiumServer();

	@SuppressWarnings("rawtypes")
	public AppiumDriver createDriver(ITestContext context) throws MalformedURLException {
		
		AppiumDriver driver = null;
		DesiredCapabilities clientDc = new DesiredCapabilities();
		DesiredCapabilities serverDc = new DesiredCapabilities();

		Map<String, String> parameters = context.getCurrentXmlTest().getAllParameters();
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String prop = entry.getKey();
			if (prop.startsWith("clientCapabilities.")) {
				clientDc.setCapability(prop.substring(prop.lastIndexOf(".") + 1), entry.getValue());
			}
			if (prop.startsWith("serverCapabilities.")) {
				serverDc.setCapability(prop.substring(prop.lastIndexOf(".") + 1), entry.getValue());
			}
		}

		int port = Integer.parseInt(context.getCurrentXmlTest().getParameter("appium.port"));
		String serverIP = context.getCurrentXmlTest().getParameter("appium.ServerIP");
		
		logger.info("Server IP: [" + serverIP + "]");

		Map<String, Object> serverCaps = new HashMap<>();
		serverCaps.put("port", port);
		serverCaps.put("ServerIP", serverIP);
		serverCaps.put("serverCapabilities", serverDc);
		if (!appiumServer.createAppiumServer(serverCaps)) {
			throw new IllegalArgumentException("- Appium server not running");
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		URL url = new URL("http://" + serverIP + ":" + port + "/wd/hub/");

		String platform = context.getCurrentXmlTest().getParameter("clientCapabilities.platformName");
		logger.debug("aPlatform: " + platform);

		if (platform.equalsIgnoreCase("android")) {
			driver = new AndroidDriver(url, clientDc);
		} else if (platform.equalsIgnoreCase("ios")) {
			driver = new IOSDriver(url, clientDc);
		} else {
			logger.error("- Platform not found");
			throw new IllegalArgumentException();
		}

		String implicitlyWait = context.getCurrentXmlTest().getParameter("appium.implicitlyWait");
		if (implicitlyWait != null && !implicitlyWait.isEmpty()) {
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(implicitlyWait), TimeUnit.SECONDS);
			logger.debug("Implicit wait set to " + implicitlyWait);
		}

		return driver;
	}

	public void closeServer() {
		appiumServer.closeAppiumServer();
	}
}
