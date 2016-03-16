package video;

import java.util.concurrent.ConcurrentLinkedDeque;


public class Client {
    public static void main(String[] args){ 
    	 ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();	//Useless at the moment
  
         DownloadVideo task = new DownloadVideo(list);
         Thread thread = new Thread(task);
         thread.start();
         try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         ReadVideo task2 = new ReadVideo(list);
         Thread thread2= new Thread(task2);
         thread2.start();
    } 
}