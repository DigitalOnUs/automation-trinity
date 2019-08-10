package main.java.appium;


import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.ServerSocket;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class AppiumServer {

        private AppiumDriverLocalService service;

        public boolean createAppiumServer( Map<String,Object> aMap ) {
            int port               = (int)    aMap.get( "port" );
            String ServerIP        = (String) aMap.get( "ServerIP" );
            DesiredCapabilities dc = (DesiredCapabilities) aMap.get( "serverCapabilities" );

//        if( !checkIfServerIsRunnning( port ) ) {
            stopServer();
            return startServer( port , ServerIP , dc );
//        } else {
//            System.out.println("Appium Server already running on Port - " + port );
//            return true;
//        }
        }

        public void closeAppiumServer() {
            stopServer();
        }

        // ------ PRIVATE------
        private boolean checkIfServerIsRunnning( int port ) {
            boolean isServerRunning = false;
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(port);
                serverSocket.close();
            } catch (IOException e) {
                //If control comes here, then it means that the port is in use
                isServerRunning = true;
            } finally {
                serverSocket = null;
            }
            return isServerRunning;
        }

        private boolean startServer( int port , String anURL , DesiredCapabilities dc ) {
            try {
                AppiumServiceBuilder builder;

                //Build the Appium service
                builder = new AppiumServiceBuilder();
                builder.withIPAddress( "127.0.0.1" );
                builder.usingPort( port );
                builder.withCapabilities( dc );
                builder.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, nextFreePort().toString() );
                builder.withArgument( AndroidServerFlag.CHROME_DRIVER_PORT, nextFreePort().toString() );

                //Start the server with the builder
                service = AppiumDriverLocalService.buildService( builder );
                service.start();

                int cnt = 0;
                while( !service.isRunning() && cnt++ < 10)
                {
                    try {
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return service.isRunning();

            } catch (AppiumServerHasNotBeenStartedLocallyException e) {
                e.printStackTrace();
            }
            return false;
        }

        private void stopServer() {
            if( service != null )
                service.stop();
        }

        private synchronized Integer nextFreePort() {
            Integer port = (int) (Math.random() * 8000) + 8100;
            while (true) {
                try (ServerSocket endpoint = new ServerSocket(port)) {
                    System.out.println( "Sock listening on Port :" + port );
                    return port;
                } catch (IOException e) {
                    port = ThreadLocalRandom.current().nextInt();
                }
            }
        }
}
