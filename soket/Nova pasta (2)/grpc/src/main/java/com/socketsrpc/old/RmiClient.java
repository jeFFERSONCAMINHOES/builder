package com.socketsrpc.old;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RmiClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8081);
            ProductService service = (ProductService) registry.lookup("ProductService");

            List<Product> productsByName = service.searchByName("Laptop");
            System.out.println("Products by name: " + productsByName);

            List<Product> productsByPrice = service.searchByPrice(100);
            System.out.println("Products by price: " + productsByPrice);

            String updateResponse = service.updateStock("Laptop", 5);
            System.out.println(updateResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
