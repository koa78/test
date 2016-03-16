package video;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
    public static void transfertVideo(InputStream in, OutputStream out, boolean closeOnExit) throws IOException{
        byte buf[] = new byte[1024];
        
        int n;
        while((n=in.read(buf))!=-1){
        	out.write(buf, 0, n);
        	try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    
        if (closeOnExit){
            in.close();
            out.close();
        }
    }
}