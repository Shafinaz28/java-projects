package RealTimesSimualtion;

public class InternetBrowser extends Thread {
	
	private static int browserCount = 0; 
	
	public void run() {
		synchronized(InternetBrowser.class) { // Synchronized for shared resource
	   
		try {
			
		for(int i =1; i<=3; i++) {   // the for loop iterates 5 times, representing the playing of 5 songs.
			
			System.out.println("Internet Browser: Loading webpage"+ i +".....");
			Thread.sleep(4000); // Simulate webpage loading time
			browserCount++;
			
			System.out.println("Internet Browser: Total webpages loaded: "+browserCount);
			
		}
	} 
		catch (InterruptedException e) { // the catch block prints the music
		System.out.println("Internet Browser: Interrupted while browsing.");
	  }
	}
  }
}