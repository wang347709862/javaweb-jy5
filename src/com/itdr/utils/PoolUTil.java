package com.itdr.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class PoolUTil {
    public static ComboPooledDataSource c=new ComboPooledDataSource();
    public static ComboPooledDataSource getCon(){
        return c;
    }
}
