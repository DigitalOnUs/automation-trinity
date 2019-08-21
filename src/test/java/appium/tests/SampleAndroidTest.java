package appium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import appium.common.BaseMobileTest;
import appium.pages.MyAndroidApplicationPage;

public class SampleAndroidTest extends BaseMobileTest {

	@Test
	public void test() {
		
		logger.info("Android Test starting...");

		MyAndroidApplicationPage sampleCIPage = new MyAndroidApplicationPage(driver);
		
		Assert.assertTrue(sampleCIPage.verifyBottomRightBtn(), "- Missing Bottom Right Button");
		logger.info("- Bottom Right Button found!");
		
		Assert.assertTrue(sampleCIPage.verifyCentralImg(), "- Missing Central Image");
		logger.info("- Central Image found!");
		
		Assert.assertTrue(sampleCIPage.verifyCentralLabel(), "- Missing Central Label");
		logger.info("- Central Label found!");
		
		Assert.assertTrue(sampleCIPage.verifyTopLeftBtn(), "- Missing Top Left Button");
		logger.info("- Top Left Button found!");
		
		Assert.assertTrue(sampleCIPage.clickOnottomRightBtn(), "- Cannot click Bottom Right Button");
		logger.info("- Clicked on Bottom Right Button!");
		
		Assert.assertTrue(sampleCIPage.clickOnTopLeftBtn(), "- Cannot click Top Left Button");
		logger.info("- Clicked on Top Left Button!");

		logger.info("Android Test finished.");
	}
}
