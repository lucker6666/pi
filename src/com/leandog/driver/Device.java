package com.leandog.driver;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.uiautomator.core.UiDevice;
import com.leandog.utils.Utils.Files;

public class Device {

    private final UiDevice device;

    public Device(UiDevice device) {
        this.device = device;
    }
    
    public String getActivityAndPackageNameAsJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("activity", device.getCurrentActivityName());
            obj.put("packageName", device.getCurrentPackageName());
        } catch (JSONException e) {
            return e.getMessage();
        }
        return obj.toString();
    }

    public String getDumpedViewHierarchyAsXML() {
        try {
            device.dumpWindowHierarchy("dumpfile");
            return  Files.contentsOfFileFromPath("/data/local/tmp/dumpfile");
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    
}
