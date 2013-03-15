package com.leandog.http;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.nanohttpd.NanoHTTPD;

public class SimpleAndroidServer extends NanoHTTPD{

    public static final String MIME_JSON = "application/json";
    private boolean isStopped;
    private final UiDevice device;

    public SimpleAndroidServer(int port,UiDevice device) throws IOException {
        super(port,new File("."));
        this.device = device;
    }
    
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        String msg = "<html><body><h1>Hello Dave</h1></body></html>";
        if("/stop".equals(uri)) {
            stop();
        }else {
            String currentActivityName = device.getCurrentActivityName();
            msg = "{\"activity\":\"" + currentActivityName +"\"}";
        }
        return new NanoHTTPD.Response(HTTP_OK,MIME_JSON,msg);
    }

    public boolean isStopped() {
        return isStopped;
    }
    
    @Override
    public void stop() {
        super.stop();
        this.isStopped = true;
    }

}
