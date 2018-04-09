package com.test.nativemethod;

public class OwnNative {
    static{
        System.load("E:\\project_sync_repository\\learn-java\\learnAdvanced\\src\\main\\resources\\java_native.dll");
    }

    public native static void say();
    public native static int sum(int a,int b);
}
