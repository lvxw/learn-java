package com.test;

import java.util.Calendar;

public class App {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MILLISECOND));
    }
}
