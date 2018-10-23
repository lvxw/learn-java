package com.test.scoket;

import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception{
        int length = 10000000;

        Socket[] socket = new Socket[length];
        for(int i = 0;i<length;i++){
            socket[i] = new Socket("localhost",8080);
            System.out.println("第"+(i+1)+"次连接成功！");
        }
        Thread.sleep(60000);
        for(int i=0;i<length;i++){
            socket[i].close();
        }
    }
}
