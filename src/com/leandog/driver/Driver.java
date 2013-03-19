package com.leandog.driver;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.leandog.http.SimpleAndroidServer;

public class Driver extends UiAutomatorTestCase {
    
    public void testDriver() throws Exception {
        SimpleAndroidServer simpleAndroidServer = new SimpleAndroidServer(54767,new Device(getUiDevice()));
        while(!simpleAndroidServer.isStopped());
    }

}
