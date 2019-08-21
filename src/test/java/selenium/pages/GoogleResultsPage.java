package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import selenium.base.BasePage;

public class GoogleResultsPage extends BasePage {

	private By wikiLink = By.partialLinkText("Wikipedia");
	
	public GoogleResultsPage(WebDriver d) {
		super(d);
	}
	
	public void clickWikiLink() {
		clickElement(wikiLink);
		log("Click on wiki link");
	}
}
