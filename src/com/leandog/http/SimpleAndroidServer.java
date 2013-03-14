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
        return super.serve(uri, method, header, parms, files);
    }

}
