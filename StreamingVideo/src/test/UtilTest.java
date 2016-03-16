package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import video.Util;

public class UtilTest {

	@Test
	public void testTransfertVideo() throws IOException {
		File fileI = new File("Xperia.avi");
		File fileO = new File("Xperia2.avi");
		FileInputStream inputFile = new FileInputStream(fileI);
		FileOutputStream outputFile = new FileOutputStream(fileO);
		
		if(inputFile.getChannel().size() == outputFile.getChannel().size()){
			Util.transfertVideo(inputFile, outputFile, true);
		}
		
		else{
			fail("Bad diffusion");
		}
	}
}
