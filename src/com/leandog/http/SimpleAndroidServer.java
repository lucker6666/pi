package com.leandog.http;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.nanohttpd.NanoHTTPD;

public class SimpleAndroidServer extends NanoHTTPD{

    public SimpleAndroidServer(int port) throws IOException {
        super(port,new File("."));
    }
    
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        String msg = "<html><body><h1>Hello Dave</h1></body></html>";
        System.out.println(msg);
        return new NanoHTTPD.Response(HTTP_OK,MIME_HTML,msg);
    }

}