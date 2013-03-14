package com.leandog.driver;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.leandog.http.SimpleAndroidServer;

public class Driver extends UiAutomatorTestCase {
    
    
    public void testDriver() throws Exception {
        new SimpleAndroidServer(54767);
        
        while(true);
    }

}
