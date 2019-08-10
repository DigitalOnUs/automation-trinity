package test.java.appium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.appium.common.BaseTest;
import test.java.appium.pages.SampleCI_Page;

public class SampleCITests extends BaseTest {

    @Test
    public void test01()
    {
        logger.info( "test01 starting----" );

        SampleCI_Page sampleCI_page = new SampleCI_Page( driver );
        Assert.assertTrue( sampleCI_page.verifyBottomRightBtn() , "- Missing Bottom Right Button" );
        logger.info( "- Bottom Right Button found!" );
        Assert.assertTrue( sampleCI_page.verifyCentralImg()     , "- Missing Central Image" );
        logger.info( "- Central Image found!" );
        Assert.assertTrue( sampleCI_page.verifyCentralLabel()   , "- Missing Central Label" );
        logger.info( "- Central Label found!" );
        Assert.assertTrue( sampleCI_page.verifyTopLeftBtn()     , "- Missing Top Left Button" );
        logger.info( "- Top Left Button found!" );
        Assert.assertTrue( sampleCI_page.clickOnottomRightBtn() , "- Cannot click Bottom Right Button" );
        logger.info( "- Clicked on Bottom Right Button!" );
        Assert.assertTrue( sampleCI_page.clickOnTopLeftBtn()    , "- Cannot click Top Left Button" );
        logger.info( "- Clicked on Top Left Button!" );

        logger.info( "test01 finished----" );
    }
}
