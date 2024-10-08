/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws Exception {
        // Create and export the remote object
        
        MyRemoteInterface remoteObject = new MyRemoteObject();
        
        // Bind the remote object to the RMI registry
        Registry registry = LocateRegistry.createRegistry(1090);
        registry.rebind("MyRemoteObject", remoteObject);
        
        System.out.println("Server ready");
    }
}