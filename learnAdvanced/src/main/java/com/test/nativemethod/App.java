package com.test.nativemethod;

public class App {
    public static void main(String[] args) throws Exception{
        int a = 2;
        a = OwnNative.sum(10, 20);
        OwnNative.say();
        System.out.println(a);
    }
}
