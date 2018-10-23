package com.rpc.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceImpl implements IService {
    private int port;
    private static Map<String,Class> repertoryMap = new HashMap<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static boolean isRunning = false;

    public ServiceImpl(int port) {
        this.port = port;
    }

    @Override
    public void register(Class service, Class serviceImpl) {
        repertoryMap.put(service.getName(),serviceImpl);
    }

    @Override
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();;
        serverSocket.bind(new InetSocketAddress("localhost",port));

        isRunning = true;

        while (true){
            System.out.println("start server......");
            Socket socket = serverSocket.accept();
            executor.execute(new ServiceTask(socket));
        }
    }

    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    public static class ServiceTask implements Runnable{
        Socket socket;

        public ServiceTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                String  serviceName = ois.readUTF();
                String methodName = ois.readUTF();
                Class[] paramTypeArr = (Class[]) ois.readObject();
                Object[] paramArr = (Object[]) ois.readObject();

                Class serviceImplCls = repertoryMap.get(serviceName);
                Method method = serviceImplCls.getMethod(methodName, paramTypeArr);
                Object result = method.invoke(serviceImplCls.newInstance(), paramArr);

                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    try {
                        ois.close();
                        oos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
