package com.leandog.http;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Test;

import com.nanohttpd.NanoHTTPD.Response;


public class SimpleAndroidServerTest {

    private SimpleAndroidServer server;

    @Test
    public void itProvidesAResponse() throws IOException {
        server = new SimpleAndroidServer(10000);
        Response serve = server.serve("/", "/", new Properties(), new Properties(), new Properties());
        assertNotNull(serve);
    }
    
    @Test
    public void itsResponseMIMETypeIsJson() throws IOException {
        server = new SimpleAndroidServer(10000);
        Response serve = server.serve("/", "/", new Properties(), new Properties(), new Properties());
        assertEquals(SimpleAndroidServer.MIME_JSON, serve.mimeType);
    }
    
    
    @After
    public void shutDown() {
        if(server != null)
        server.stop();
    }
}
