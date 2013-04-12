package com.brbw.driver;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;

import com.android.uiautomator.core.UiDevice;
import com.brbw.utils.Utils.Files;

public class Device {

    private static final String DUMP_FILE_BASE_DIR = "local/tmp";
    private static final String DUMP_FILE_NAME = "dumpfile.xml";
    private final UiDevice device;

    public Device(UiDevice device) {
        this.device = device;
    }
    
    @SuppressWarnings("deprecation")
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
            File file = new File(getBaseDir(), DUMP_FILE_NAME);
            if(file.exists()) {
                file.delete();
            }
            device.dumpWindowHierarchy(DUMP_FILE_NAME);
            return  Files.contentsOfFileFromPath(file);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private File getBaseDir() {
        return new File(Environment.getDataDirectory(), DUMP_FILE_BASE_DIR);
    }
    
}
