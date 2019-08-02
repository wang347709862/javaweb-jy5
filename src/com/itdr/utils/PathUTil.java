package com.itdr.utils;

public class PathUTil {
    public static String getPath(String path){
        path.replace(".","/");
        String[] s = path.split("/");
        return s[1];
    }
}
