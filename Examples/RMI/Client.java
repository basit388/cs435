/*
The client connects to the registry, looksup the MyRemoteObject 
and invokes the sayHello method remotely. The message returned from the server
is displayed
 */
package RMI;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception {
        // Look up the remote object from the RMI registry
        Registry registry = LocateRegistry.getRegistry("localhost", 1090);
        
        MyRemoteInterface remoteObject = 
                (MyRemoteInterface) registry.lookup("MyRemoteObject");
        
        // Invoke remote method
        String result = remoteObject.sayHello();
        System.out.println("Result from server: " + result);
    }
}