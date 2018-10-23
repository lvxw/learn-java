package com.fanxing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<? super Number> listInteger =new ArrayList<Number>();
        List<String> listString =new ArrayList<String>();

        printCollection(listInteger);
        printCollection(listString);
    }

    public static void printCollection(Collection <?> collection){
        for(Object obj:collection){
            System.out.println(obj);
        }
    }
}
