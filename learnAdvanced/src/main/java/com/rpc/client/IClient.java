package com.rpc.client;

import com.rpc.service.IGreetingService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public interface IClient {

     <T> T getRemoteProxyObj(Class serviceCls, InetSocketAddress inetSocketAddress);
}
