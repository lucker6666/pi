package com.brbw.http;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.brbw.driver.Device;
import com.nanohttpd.NanoHTTPD;

public class SimpleAndroidServer extends NanoHTTPD{

    public static final String MIME_JSON = "application/json";
    private boolean isStopped;
    private final Device device;

    public SimpleAndroidServer(int port,Device device) throws IOException {
        super(port,new File("."));
        this.device = device;
    }
    
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        if("/stop".equals(uri)) {
            stop();
        }else if("/dumpWindow".equals(uri)) {
            new NanoHTTPD.Response(HTTP_OK, MIME_XML, device.getDumpedViewHierarchyAsXML());
        }else if ("/currentActivity".equals(uri)){
            return new NanoHTTPD.Response(HTTP_OK,MIME_JSON, device.getActivityAndPackageNameAsJson());
        }
        return new NanoHTTPD.Response(HTTP_OK,MIME_JSON,"");
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
