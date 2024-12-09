package com.socketsrpc.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class SocketServer {
    private static List<Product> productCatalog = new ArrayList<>();

    public static void main(String[] args) {
        productCatalog.add(new Product("Laptop", 1500.0, 10));
        productCatalog.add(new Product("Mouse", 25.0, 50));
        productCatalog.add(new Product("Keyboard", 100.0, 30));

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is running...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected.");
                    handleClient(clientSocket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



private static void handleClient(Socket clientSocket) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
    Gson gson = new Gson();

    String request = input.readLine();
    if (request != null) {
        System.out.println("Received request: " + request);

        if (request.startsWith("SEARCH_BY_NAME")) {
            String name = request.split(":")[1];
            List<Product> results = searchByName(name);
            output.println(gson.toJson(results));
        } else if (request.startsWith("SEARCH_BY_PRICE")) {
            double price = Double.parseDouble(request.split(":")[1]);
            List<Product> results = searchByPrice(price);
            output.println(gson.toJson(results));
        } else if (request.startsWith("UPDATE_STOCK")) {
            String[] parts = request.split(":");
            String name = parts[1];
            int stock = Integer.parseInt(parts[2]);
            String response = updateStock(name, stock);
            output.println(response);
        }
    } else {
        System.out.println("No request received.");
    }

    clientSocket.close();
    }

    

    private static List<Product> searchByName(String name) {
        return productCatalog.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .toList();
    }

    private static List<Product> searchByPrice(double maxPrice) {
        return productCatalog.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .toList();
    }

    private static String updateStock(String name, int stock) {
        for (Product product : productCatalog) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setStock(stock);
                return "Stock updated for " + name;
            }
        }
        return "Product not found!";
    }
}
