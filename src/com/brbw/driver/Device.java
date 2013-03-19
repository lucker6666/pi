package com.brbw.driver;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.uiautomator.core.UiDevice;
import com.brbw.utils.Utils.Files;

public class Device {

    private static final String DUMP_FILE_PATH = "/data/local/tmp/dumpfile";
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
            File file = new File(DUMP_FILE_PATH);
            if(file.exists()) {
                file.delete();
            }
            device.dumpWindowHierarchy("dumpfile");
            return  Files.contentsOfFileFromPath(DUMP_FILE_PATH);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    
}
