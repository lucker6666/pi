package com.leandog.http;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import com.nanohttpd.NanoHTTPD.Response;


public class SimpleAndroidServerTest {

    @Test
    public void itProvidesAResponse() throws IOException {
        SimpleAndroidServer server = new SimpleAndroidServer(10000);
        Response serve = server.serve("/", "/", new Properties(), new Properties(), new Properties());
        assertNotNull(serve);
    }
}
