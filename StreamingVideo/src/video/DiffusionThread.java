package video;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class DiffusionThread extends Thread {
	
	private Socket socket;
	
	public DiffusionThread (Socket socket) {
		this.socket = socket;
	}
	
	public void run () {
		 // Envoi video 
        try {
			Util.transfertVideo(
					 new FileInputStream("Xperia.avi"),
			         socket.getOutputStream(),
			         socket
			         );
		} catch (SocketException e) {
        	System.err.println(this.getId() + ": " + e);
        	System.out.println("\n Client " + socket.getInetAddress() +" déconnecté !");
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
