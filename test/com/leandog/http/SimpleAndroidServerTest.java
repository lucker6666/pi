package com.leandog.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Test;

import com.android.uiautomator.core.UiDevice;
import com.nanohttpd.NanoHTTPD.Response;

public class SimpleAndroidServerTest {

    SimpleAndroidServer server;
    UiDevice uiDevice = mock(UiDevice.class);

    @Test
    public void itProvidesAResponse() throws IOException {
        server = new SimpleAndroidServer(10000, uiDevice);
        Response serve = server.serve("/", "/", new Properties(), new Properties(), new Properties());
        assertNotNull(serve);
    }

    @Test
    public void itsResponseMIMETypeIsJson() throws IOException {
        server = new SimpleAndroidServer(10000, uiDevice);
        Response serve = server.serve("/", "/", new Properties(), new Properties(), new Properties());
        assertEquals(SimpleAndroidServer.MIME_JSON, serve.mimeType);
    }

    @Test
    public void itStopsWhenCommanded() throws IOException {
        server = new SimpleAndroidServer(10000, uiDevice);
        server.serve("/stop", "GET", new Properties(), new Properties(), new Properties());
        assertTrue(server.isStopped());
    }
    
    @Test
    public void itTellsMeTheActivityAndPackageImCurrentlyOn() throws IOException{
        when(uiDevice.getCurrentActivityName()).thenReturn("MainActivity");
        when(uiDevice.getCurrentPackageName()).thenReturn("com.android");
        server = new SimpleAndroidServer(10000, uiDevice);
        Response response = server.serve("/currentActivity", "GET", new Properties(), new Properties(), new Properties());
        InputStream data = response.data;
        String activityJson = Utils.Strings.stringFrom(data);
        assertEquals("{\"activity\":\"MainActivity\",\"packageName\":\"com.android\"}", activityJson);
    }

    @After
    public void shutDown() {
        if (server != null)
            server.stop();
    }
}
