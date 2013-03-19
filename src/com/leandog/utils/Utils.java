package com.leandog.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static class Strings {
        public static String stringFrom(InputStream data) throws IOException {
            StringBuilder builder = new StringBuilder();
            int b = data.read();
            while (b > -1) {
                builder.append((char) b);
                b = data.read();
            }
            data.close();
            return builder.toString();
        }
    }
    
    public static class Files {
        public static String contentsOfFileFromPath(String path) throws IOException {
            return Strings.stringFrom(new FileInputStream(new File(path)));
        }
    }
}