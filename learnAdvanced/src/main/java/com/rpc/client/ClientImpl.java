package com.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientImpl implements IClient {

    @Override
    public <T> T getRemoteProxyObj(Class serviceCls, InetSocketAddress inetSocketAddress){

        return (T)Proxy.newProxyInstance(serviceCls.getClassLoader(), new Class<?>[]{serviceCls} , new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket();
                socket.connect(inetSocketAddress);

                ObjectOutputStream oos = null;
                ObjectInputStream ois = null;

                try {
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeUTF(serviceCls.getName());
                    oos.writeUTF(method.getName());
                    oos.writeObject(method.getParameterTypes());
                    oos.writeObject(args);

                    ois = new ObjectInputStream(socket.getInputStream());
                    return ois.readObject();
                } catch (Exception e) {
                   e.printStackTrace();
                   return null;
                }finally {
                    try {
                        oos.close();
                        ois.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
