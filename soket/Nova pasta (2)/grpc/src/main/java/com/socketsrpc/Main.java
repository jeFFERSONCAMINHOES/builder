package com.socketsrpc;

public class Main {

    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.createSocket(8080);
    }
}