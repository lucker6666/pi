package com.brbw.driver;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.brbw.http.SimpleAndroidServer;

public class Driver extends UiAutomatorTestCase {
    
    public void testDriver() throws Exception {
        SimpleAndroidServer simpleAndroidServer = new SimpleAndroidServer(54767,new Device(getUiDevice()));
        while(!simpleAndroidServer.isStopped());
    }

}
