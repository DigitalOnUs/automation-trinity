package appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

@SuppressWarnings("rawtypes")
public class MyAndroidApplicationPage extends BaseMobilePage {

	@AndroidFindBy(id = "com.vitacost.myapplication:id/top-left-button")
	@iOSXCUITFindBy(accessibility = "top-left-button")
	private MobileElement topLeft_btn;

	@AndroidFindBy(id = "com.vitacost.myapplication:id/central_image")
	@iOSXCUITFindBy(accessibility = "central-image")
	private MobileElement central_img;

	@AndroidFindBy(id = "com.vitacost.myapplication:id/textView")
	@iOSXCUITFindBy(accessibility = "central-label")
	private MobileElement central_lbl;

	@AndroidFindBy(id = "com.vitacost.myapplication:id/bottom-right-button")
	@iOSXCUITFindBy(accessibility = "bottom-right-button")
	private MobileElement bottomRight_btn;

	// ------- METHODS -------
	public MyAndroidApplicationPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public boolean verifyTopLeftBtn() {
		return waitForElementToBeVisible(topLeft_btn, 3000);
	}

	public boolean clickOnTopLeftBtn() {
		try {
			clickOnElement(topLeft_btn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean verifyBottomRightBtn() {
		return waitForElementToBeVisible(bottomRight_btn, 3000);
	}

	public boolean clickOnottomRightBtn() {
		try {
			clickOnElement(bottomRight_btn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean verifyCentralImg() {
		return waitForElementToBeEnabled(central_img, 3000);
	}

	public boolean verifyCentralLabel() {
		return waitForElementToBeVisible(central_lbl, 3000);
	}
}
