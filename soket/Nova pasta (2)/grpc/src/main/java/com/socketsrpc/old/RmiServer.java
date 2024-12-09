package com.socketsrpc.old;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RmiServer extends UnicastRemoteObject implements ProductService {
    private List<Product> productCatalog;

    protected RmiServer() throws RemoteException {
        productCatalog = new ArrayList<>();
        productCatalog.add(new Product("Laptop", 1500.0, 10));
        productCatalog.add(new Product("Mouse", 25.0, 50));
    }

    @Override
    public List<Product> searchByName(String name) throws RemoteException {
        return productCatalog.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .toList();
    }

    @Override
    public List<Product> searchByPrice(double maxPrice) throws RemoteException {
        return productCatalog.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .toList();
    }

    @Override
    public String updateStock(String name, int stock) throws RemoteException {
        for (Product product : productCatalog) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setStock(stock);
                return "Stock updated for " + name;
            }
        }
        return "Product not found!";
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ProductService", new RmiServer());
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
