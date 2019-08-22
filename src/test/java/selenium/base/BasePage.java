package selenium.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public abstract class BasePage {

	protected WebDriver driver;
	
	public BasePage(WebDriver d) {
		driver = d;
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	protected void goToUrl(String url) {
		driver.get(url);
	}
	
	protected void clickElement(By locator) {
		driver.findElement(locator).click();
	}
	
	protected void typeElement(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}
	
	protected String getElementText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	protected JavascriptExecutor getJsExecutor() {
		return (JavascriptExecutor) driver;
	}
	
	protected void log(String message) {
		System.out.println(message);
		Reporter.log(message);
	}
}
