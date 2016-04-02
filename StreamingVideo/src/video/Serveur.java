package video;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) throws IOException 
    { 
    	// Initialisation de la socket
    	ServerSocket serverSocket = null;
    	Socket socket = null;
    	
    	try {
    		serverSocket = new ServerSocket(Constants.PORT);
    	}
    	catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	while (true) {
    		try{
    			socket = serverSocket.accept();
    		}
    		catch (IOException e) {
    			System.out.println("I/O Error : " + e);
    		}
    		
    		System.out.println("Client " + socket.getInetAddress() + " connecté !");
    		
    		new DiffusionThread (socket).start();
    	}
    } 
}