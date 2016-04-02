package video;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Util {
    public static void transfertVideo(InputStream in, OutputStream out, Socket socket) throws IOException{
        byte buf[] = new byte[1024];
        
        int n;
        while((n=in.read(buf))!=-1){
	        if (socket.isClosed()) {	
        		out.write(buf, 0, n);
	        	try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
				}
	        }
	        else
	        	break;
        }
    
        in.close();
        out.close();
        socket.close();
    }
}