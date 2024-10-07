/*

This client program initiates a socket binds to port 6789 on localhost.
It sends a user provided String to a server program.
It receives the response from the server and displays it to screen

*/
package sockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

class TCPClient { 
    public static void main(String [] args) throws Exception
    {
        String Str;
        String modifiedSentence; 
        
        
        int port = 6789;
	Scanner In = new Scanner(System.in);
        Socket cs = new Socket("localhost", port); 
        
        System.out.println("---Client connection initiated at port: " + port +" ---");
        
        DataOutputStream outToServer = new DataOutputStream(cs.getOutputStream());
        
        InputStreamReader StreamIn = new InputStreamReader(cs.getInputStream());

        BufferedReader inFromServer = new BufferedReader(StreamIn); 

        System.out.println("Type your message: ");
        Str = In.nextLine();
        //send Str to server
        
        System.out.println("Sending \"" + Str + "\" to serrver");
        outToServer.writeBytes(Str + '\n');
        
        //Listen to server response
        
        System.out.println("Listening to server: ");
        modifiedSentence = inFromServer.readLine();
        
        System.out.println(port + " : " + modifiedSentence);
        
        System.out.println("---Closing connection---");
        cs.close();
    } 
} 



