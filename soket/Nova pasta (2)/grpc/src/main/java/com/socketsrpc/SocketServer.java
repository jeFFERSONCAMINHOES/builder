package com.socketsrpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    
    public void createSocket(int PORT){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[Server] is running on port " + PORT + "...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("[Server] Client connected: " + clientSocket.getInetAddress());

                    String message;
                    while ((message = input.readLine()) != null) {
                        System.out.println("[Server] Message from client: " + message);
                        output.println("[Server]"+message);
                    }

                    System.out.println("[Server] Client disconnected.");
                } catch (IOException e) {
                    System.err.println("[Server] Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("[Server] Server error: " + e.getMessage());
        }
    }
}

