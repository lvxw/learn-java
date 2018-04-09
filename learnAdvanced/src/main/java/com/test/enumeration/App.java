package com.test.enumeration;

public class App {
    public static void main(String[] args) {
        System.out.println(Color.getName(1));
        Day day =Day.MONDAY;
        System.out.println(Day.valueOf("MONDAY") == Day.MONDAY);
        System.out.println(Integer.toString(10));
    }
}
