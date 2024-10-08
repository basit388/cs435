/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteObject extends UnicastRemoteObject 
        implements MyRemoteInterface {
    public MyRemoteObject() throws RemoteException {
        super();
    }

    // Implementation of the remote method
    public String sayHello() throws RemoteException {
        return "Hello from server!";
    }
    
}