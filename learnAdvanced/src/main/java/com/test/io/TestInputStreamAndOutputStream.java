package com.test.io;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class TestInputStreamAndOutputStream {
    private static InputStream is = TestInputStreamAndOutputStream.class.getResourceAsStream("/imgs/timg.jpg");


    public static void copyFile() throws Exception{
        OutputStream os = new FileOutputStream("tmp/2.jpg");

        int len = 0;
        int size = 1024;
        byte[] bytes = new byte[size];
        while ((len = is.read(bytes)) != -1){
            os.write(bytes, 0, len);
        }

        os.close();
        is.close();
    }

    public static void main(String[] args) throws Exception{
        copyFile();
    }
}
