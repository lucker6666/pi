package com.brbw.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static class Strings {
        public static String stringFrom(InputStream data) throws IOException {
            StringBuilder builder = new StringBuilder();
            for(int b = data.read(); b > -1; b = data.read()) {
                builder.append((char) b);
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