package com.test.io;

import java.io.*;

public class TestReaderAndWriter {

    public static void copyFile() throws Exception{
        InputStreamReader isr = new InputStreamReader(TestReaderAndWriter.class.getResourceAsStream("/logs/1.txt"));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("tmp/1.txt"));

        int len;
        int size =1024;
        char[] ch = new char[size];

        while ((len = isr.read(ch)) != -1){
            osw.write(ch, 0, len);
        }

        osw.close();
        isr.close();

    }

    public static void main(String[] args) throws Exception {
        copyFile();
    }
}
