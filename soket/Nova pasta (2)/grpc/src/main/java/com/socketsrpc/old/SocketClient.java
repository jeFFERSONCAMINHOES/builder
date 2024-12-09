package com.socketsrpc.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SocketClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

        
                    
            Gson gson = new Gson();

            output.println("SEARCH_BY_NAME:Laptop");
            String response = input.readLine();
            if (response != null) {
                List<Product> products = gson.fromJson(response, new TypeToken<List<Product>>(){}.getType());
                products.forEach(product -> System.out.println(product));
            }

            output.println("SEARCH_BY_PRICE:100");
            response = input.readLine();
            if (response != null) {
                List<Product> products = gson.fromJson(response, new TypeToken<List<Product>>(){}.getType());
                products.forEach(product -> System.out.println(product));
            }

            output.println("UPDATE_STOCK:Laptop:20");
            System.out.println("Response: " + input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
