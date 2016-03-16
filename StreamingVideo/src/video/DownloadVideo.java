package video;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DownloadVideo implements Runnable {
 
    private ConcurrentLinkedDeque<String> list; // on pushera les paquets dans cette liste
 
    public DownloadVideo(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }
 
    @Override
    public void run() {
    	Socket sock;
		try {
			sock = new Socket(InetAddress.getLocalHost(),9001);
			// Reception video
	        Util.transfertVideo(
	        		sock.getInputStream(),
	                new FileOutputStream("Xperia2.avi"),
	                true);
	        
	        sock.close();
		}
		catch (UnknownHostException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
    }
}
