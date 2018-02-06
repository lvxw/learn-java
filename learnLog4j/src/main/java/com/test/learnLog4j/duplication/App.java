package com.test.learnLog4j.duplication;

import org.apache.log4j.Logger;

public class App {
    private static Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) {
        System.out.println(123);
        System.out.println(123);
        logger.error("123");
        logger.error("123");
    }
}
