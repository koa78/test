package video;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class ReadVideo implements Runnable {
 
    private ConcurrentLinkedDeque<OutputStream[]> list; // On removera les premiers paquets de la liste
 
    public ReadVideo(ConcurrentLinkedDeque<OutputStream[]> list) {
        this.list = list;
    }
 
    @Override
    public void run() {
    	JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		// Cr�ation Canvas pour supporter le media player
		Canvas c = new Canvas();
		c.setBackground(Color.BLACK);
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(c);
		f.add(p);
		
		// Chercher la librairie VLC
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"/Applications/VLC.app/Contents/MacOS/lib");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
		// Cr�tion du Media Player
		MediaPlayerFactory mpf = new MediaPlayerFactory();
		EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
		emp.setFullScreen(true);
		emp.setVideoSurface(mpf.newVideoSurface(c));
		
		String file = "Xperia2.avi";
		
		emp.prepareMedia(file);
		// Lancement de la vid�o 
		emp.play();
		emp.setFullScreen(false);
		emp.setFullScreen(true);
    }
}
