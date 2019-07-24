package main.java.appium;

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

public class DriverCreator {

    final static Logger logger = Logger.getLogger( DriverCreator.class );
    private static AppiumServer anAppSvr = new AppiumServer();

    public static AppiumDriver createDriver( ITestContext context ) throws MalformedURLException {
        AppiumDriver        driver   = null;
        DesiredCapabilities clientDc = new DesiredCapabilities();
        DesiredCapabilities serverDc = new DesiredCapabilities();

        Map<String, String> parameters = context.getCurrentXmlTest().getAllParameters();
        for ( Map.Entry<String, String> entry : parameters.entrySet() )
        {
            String prop = entry.getKey();
            if ( prop.startsWith( "clientCapabilities." ) ) {
                clientDc.setCapability( prop.substring( prop.lastIndexOf("." ) + 1 ) , entry.getValue() );
            }
            if ( prop.startsWith( "serverCapabilities." ) ) {
                serverDc.setCapability( prop.substring( prop.lastIndexOf("." ) + 1 ) , entry.getValue() );
            }

        }

        int port = Integer.parseInt( context.getCurrentXmlTest().getParameter("appium.port" ) );
        String ServerIP = context.getCurrentXmlTest().getParameter("appium.ServerIP" );
        logger.info( "ServerIP: [" + ServerIP + "]" );

        Map<String,Object> serverCaps = new HashMap<>();
        serverCaps.put( "port"                , port );
        serverCaps.put( "ServerIP"            , ServerIP );
        serverCaps.put( "serverCapabilities"  , serverDc );
        if( !anAppSvr.createAppiumServer( serverCaps ) )
            throw new IllegalArgumentException( "- Appium server not running" );

        try {
            Thread.sleep( 5000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL anURL = new URL( "http://" + ServerIP + ":" + port + "/wd/hub/"  );

        String aPlatform = context.getCurrentXmlTest().getParameter("clientCapabilities.platformName" );
        logger.debug( "aPlatform: " + aPlatform );

        if( aPlatform.equalsIgnoreCase( "android" ) ) {
            driver = new AndroidDriver( anURL , clientDc );
        }
        else if( aPlatform.equalsIgnoreCase( "ios" ) ) {
            driver = new IOSDriver( anURL , clientDc );
        }
        else {
            logger.error( "- Platform not found" );
            throw new IllegalArgumentException();
        }

        String implicitlyWait = context.getCurrentXmlTest().getParameter("appium.implicitlyWait" );
        if( implicitlyWait != null && !implicitlyWait.isEmpty() ) {
            driver.manage().timeouts().implicitlyWait( Integer.parseInt( implicitlyWait ) , TimeUnit.SECONDS );
            logger.debug( "Implicit wait set to " + implicitlyWait );
        }

        return driver;
    }

    public void closeServer() {
        anAppSvr.closeAppiumServer();
    }
}
