package reseau;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class Client {
    public static void main(String[] args) throws IOException 
    { 
    	//Initialisation de la socket
        Socket sock = new Socket(InetAddress.getLocalHost(),9001);
    
        // Reception video
        Util.transfertVideo(
        		sock.getInputStream(),
                new FileOutputStream("Xperia2.avi"),
                true);
        
        sock.close();
        
        JFrame f = new JFrame();
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		// Création Canvas pour supporter le media player
		Canvas c = new Canvas();
		c.setBackground(Color.BLACK);
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(c);
		f.add(p);
		
		// Chercher la librairie VLC
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
		// Crétion du Media Player
		MediaPlayerFactory mpf = new MediaPlayerFactory();
		EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
		emp.setVideoSurface(mpf.newVideoSurface(c));
		
		String file = "Xperia2.avi";
		
		emp.prepareMedia(file, null);
		// Lancement de la vidéo 
		emp.play();
    } 
}