package com.brbw.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.brbw.driver.Device;
import com.brbw.utils.Utils;
import com.nanohttpd.NanoHTTPD.Response;

public class SimpleAndroidServerTest {

    SimpleAndroidServer server;
    Device uiDevice = mock(Device.class);

    @Before
    public void setup() throws Exception {
        server = new SimpleAndroidServer(10000, uiDevice);
    }

    @Test
    public void itProvidesAResponse() {
        Response serve = server.serve("/", "/", new Properties(), new Properties(), new Properties());
        assertNotNull(serve);
    }

    @Test
    public void itsResponseMIMETypeIsJson() {
        Response serve = server.serve("/", "/", new Properties(), new Properties(), new Properties());
        assertEquals(SimpleAndroidServer.MIME_JSON, serve.mimeType);
    }

    @Test
    public void on404returnsFailedUri() {

    }

    @Test
    public void itStopsWhenCommanded() {
        server.serve("/stop", "GET", new Properties(), new Properties(), new Properties());
        assertTrue(server.isStopped());
    }

    @Test
    public void itTellsMeThePackageImCurrentlyOn() throws Exception {
        returnActivityAndPackageName();
        Response response = server.serve("/currentActivity", "GET", new Properties(), new Properties(), new Properties());
        JSONObject json = jsonFrom(response);
        assertEquals("com.android", json.get("packageName"));
    }

    @Test
    public void itTellsMeTheActivityImCurrentlyOn() throws Exception {
        returnActivityAndPackageName();
        Response response = server.serve("/currentActivity", "GET", new Properties(), new Properties(), new Properties());
        JSONObject json = jsonFrom(response);
        assertEquals("MainActivity", json.get("activity"));
    }

    private JSONObject jsonFrom(Response response) throws IOException, JSONException {
        String activityJson = Utils.Strings.stringFrom(response.data);
        return new JSONObject(activityJson);
    }

    private void returnActivityAndPackageName() throws JSONException {
        String json = new JSONObject().put("packageName", "com.android").put("activity", "MainActivity").toString();
        when(uiDevice.getActivityAndPackageNameAsJson()).thenReturn(json);
    }

    @After
    public void shutDown() {
        if (server != null)
            server.stop();
    }
}
