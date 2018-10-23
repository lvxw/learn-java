package com.rpc.test;

import com.rpc.client.ClientImpl;
import com.rpc.client.IClient;
import com.rpc.service.IGreetingService;

import java.net.InetSocketAddress;

public class TestClient {
    public static void main(String[] args) throws Exception {
        IClient client = new ClientImpl();

        for(int i=0;i<10;i++){
            IGreetingService service = client.getRemoteProxyObj(Class.forName("com.rpc.service.IGreetingService"), new InetSocketAddress("localhost", 9999));
            String result = service.sayHello("hello");
            System.out.println(result);
        }
    }
}
