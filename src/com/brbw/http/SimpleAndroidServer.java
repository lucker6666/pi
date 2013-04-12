package com.brbw.http;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.brbw.driver.Device;
import com.nanohttpd.NanoHTTPD;

public class SimpleAndroidServer extends NanoHTTPD {

    public static final String MIME_JSON = "application/json";
    private boolean isStopped;
    private final Device device;

    public SimpleAndroidServer(int port, Device device) throws IOException {
        super(port, new File("."));
        this.device = device;
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties params, Properties files) {
        if ("/stop".equals(uri)) {
            stop();
        } else if ("/dumpWindow".equals(uri)) {
            return new NanoHTTPD.Response(HTTP_OK, MIME_XML, device.getDumpedViewHierarchyAsXML());
        } else if ("/currentActivity".equals(uri)) {
            return new NanoHTTPD.Response(HTTP_OK, MIME_JSON, device.getActivityAndPackageNameAsJson());
        }
        return new NanoHTTPD.Response(HTTP_NOTFOUND, MIME_JSON, notFoundException(uri, method, header, params, files));
    }

    private String notFoundException(String uri, String method, Properties header, Properties params, Properties files) {
        JSONObject json = new JSONObject();
        try {
            json.put("URI", uri);
            json.put("METHOD", method);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
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
