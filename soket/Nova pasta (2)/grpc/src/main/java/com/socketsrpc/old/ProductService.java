package com.socketsrpc.old;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProductService extends Remote {
    List<Product> searchByName(String name) throws RemoteException;
    List<Product> searchByPrice(double maxPrice) throws RemoteException;
    String updateStock(String name, int stock) throws RemoteException;
}
