package com.rpc.test;

import com.rpc.service.GreetingServiceImpl;
import com.rpc.service.IGreetingService;
import com.rpc.service.IService;
import com.rpc.service.ServiceImpl;

import java.io.IOException;

public class TestServer {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                IService registerCenter = new ServiceImpl(9999);
                registerCenter.register(IGreetingService.class, GreetingServiceImpl.class);
                try {
                    registerCenter.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
