package video;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DownloadVideo implements Runnable {
 
    private ConcurrentLinkedDeque<OutputStream[]> list; // on pushera les paquets dans cette liste
 
    public DownloadVideo(ConcurrentLinkedDeque<OutputStream[]> list) {
        this.list = list;
    }
 
    @Override
    public void run() {
    	Socket sock;
		try {
			sock = new Socket(InetAddress.getLocalHost(),9001);
			// Reception video
			InputStream in = sock.getInputStream();
			OutputStream out[] = new OutputStream[1];
			out[0] = new FileOutputStream("Xperia2.avi");
			byte buf[] = new byte[1024];
		        
		    int n;
		    while((n=in.read(buf))!=-1){
		        out[0].write(buf, 0, n);
		        //Adding in the concurent linked deque
		        list.addLast(out);
		        System.out.println("byte : " + out);
		        try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		    in.close();
		    out[0].close();
	        
	        sock.close();
	        
		}
		catch (UnknownHostException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
    }
}
