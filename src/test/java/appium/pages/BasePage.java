package appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import appium.common.BaseTest;

public class BasePage {
	
	@SuppressWarnings("rawtypes")
	protected AppiumDriver driver;
	
	public final static Logger logger = Logger.getLogger(BaseTest.class);

	public boolean waitForElementToBeVisible(MobileElement anElement, long timeInMiliSecs) {
		while (timeInMiliSecs > 0) {
			try {
				return anElement.isDisplayed();
			} catch (Exception e) {
				mySleep(500);
				timeInMiliSecs = -500;
			}
		}
		return false;
	}

	public boolean waitForElementToBeEnabled(MobileElement anElement, long timeInMiliSecs) {
		while (timeInMiliSecs > 0) {
			try {
				return anElement.isEnabled();
			} catch (Exception e) {
				mySleep(500);
				timeInMiliSecs = -500;
			}
		}
		return false;
	}

	public void mySleep(int TimeInMiliSecs) {
		try {
			Thread.sleep(TimeInMiliSecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickOnElement(MobileElement anElement) {
		logger.info("Clicking on:" + anElement.toString());
		anElement.click();
	}
}
