/*

This server program initiates a Serversocket & binds it to port 6789 on localhost.
It listens and reads a string sent from the client socket.
It returns the reponse to the client by uppercasing the string

*/
package sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String [] args) throws Exception 
    {

        String Str;
        String UpperCase;
        int port = 6789;
        ServerSocket s = new ServerSocket(port);
        System.out.println("---Server Initiated at port: " + port +" ---");

        while(true) 
        {
            Socket cs = s.accept();
            System.out.println("---Incoming connection---");
            InputStreamReader StreamIn = new InputStreamReader(cs.getInputStream());
            BufferedReader in = new BufferedReader(StreamIn); 

            DataOutputStream outToClient = new DataOutputStream(cs.getOutputStream()); 
            
            //read input stream into Str
            Str = in.readLine();
            UpperCase = Str.toUpperCase() + '\n';
            System.out.println("Client: " + Str);
            System.out.println("Server: " + UpperCase);
            //write string to stream
            outToClient.writeBytes(UpperCase);
            System.out.println("---End communication---");
            
        }
    }
} 