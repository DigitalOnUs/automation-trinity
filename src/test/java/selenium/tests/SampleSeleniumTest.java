package selenium.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import selenium.base.BaseTest;
import selenium.pages.GoogleResultsPage;
import selenium.pages.GoogleSearchPage;

public class SampleSeleniumTest extends BaseTest {

	@Test(testName = "Wikipedia flow for Andre Pierre Gignac", groups = {"demo"})
	public void testMethod() throws InterruptedException {
		
		GoogleSearchPage searchPage = new GoogleSearchPage(driver);
		searchPage.open();
		searchPage.typeSearch("gignac");
		searchPage.clickSearch();
		
		GoogleResultsPage resultsPage = new GoogleResultsPage(driver);
		resultsPage.clickWikiLink();
		
		assertTrue(resultsPage.getPageTitle().contains("Wikipedia"),
				"Page found was not wikipedia. Page title was [" + resultsPage.getPageTitle() + "]");
	}
}
