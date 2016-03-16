package video;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) throws IOException 
    { 
    	// Initialisation de la socket
        Socket sock = new ServerSocket(9001).accept();
        
        // Envoi video 
        Util.transfertVideo(
        		 new FileInputStream("Xperia.avi"),
                 sock.getOutputStream(),
                 true);
        
        sock.close(); 
    } 
}