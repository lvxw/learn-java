package com.test.scoket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private ServerSocket serverSocket = null;

    public Server(int port) {
        this.port = port;
    }

    public void service() throws IOException {
        serverSocket = new ServerSocket(port,3);
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress()+":"+socket.getPort());
            socket.close();
        }
    }

    public static void main(String[] args) throws  Exception {
        Server server = new Server(8080);
        Thread.sleep(30000);
        server.service();
    }
}
