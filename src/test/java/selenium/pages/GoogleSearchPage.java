package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import selenium.base.BasePage;

public class GoogleSearchPage extends BasePage {
	
	private static final String URL = "http://www.google.com"; 
	
	private By searchTextbox = By.name("q");
	
	private By searchButton = By.name("btnK");
	
	public GoogleSearchPage(WebDriver d) {
		super(d);
	}
	
	public void open() {
		goToUrl(URL);
		log("Entering Google Page");
	}

	public void typeSearch(String text) throws InterruptedException {
		typeElement(searchTextbox, text);
		log("Entered [" + text + "] in Search textbox");
	}
	
	public void clickSearch() throws InterruptedException {
		clickElement(searchButton);
		log("Click on Search button");
	}
}
