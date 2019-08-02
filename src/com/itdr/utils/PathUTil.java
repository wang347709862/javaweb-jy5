package com.itdr.utils;

public class PathUTil {
    public static String getPath(String path){
        String newpath=path.replace(".","/");
        String[] s = newpath.split("/");
        return s[1];
    }
}
