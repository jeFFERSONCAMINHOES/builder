package com.socketsrpc;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleSocketClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 8080;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)) {
    
            System.out.println("[Client] Connected to server: " + SERVER_ADDRESS + ":" + SERVER_PORT);
            System.out.println("[Client] Type messages to send to the server (type 'exit' to quit):");
            String message;
            while (!(message = scanner.nextLine()).equalsIgnoreCase("exit")) {
                output.println(message);
                String response = input.readLine();
                System.out.println("[Client] "+response);
            }
            System.out.println("[Client] Closing connection...");

        } catch (IOException e) {
            System.err.println("[Client] error: " + e.getMessage());
        }
    }

}
