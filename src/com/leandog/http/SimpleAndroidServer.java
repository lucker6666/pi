package com.leandog.http;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.uiautomator.core.UiDevice;
import com.leandog.utils.Utils.Files;
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
        String msg = "";
        if("/stop".equals(uri)) {
            stop();
        }else if("/dumpWindow".equals(uri)) {
            msg = getDumpedViewHierarchy();
            new NanoHTTPD.Response(HTTP_OK, MIME_XML, msg);
        }else{
            try {
                JSONObject obj = new JSONObject();
                obj.put("activity", device.getCurrentActivityName());
                obj.put("packageName", device.getCurrentPackageName());
                msg = obj.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new NanoHTTPD.Response(HTTP_OK,MIME_JSON,msg);
    }

    private String getDumpedViewHierarchy() {
        try {
            device.dumpWindowHierarchy("dumpfile");
            return  Files.contentsOfFileFromPath("/data/local/tmp/dumpfile");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
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
