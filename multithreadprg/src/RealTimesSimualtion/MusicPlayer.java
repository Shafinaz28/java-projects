package RealTimesSimualtion;

public class MusicPlayer extends Thread {
    
	public void run() {       // when the thread is started run method will be executed
		try {
			for(int i =1; i<=5; i++) {   // the for loop iterates 5 times, representing the playing of 5 songs.
				System.out.println("Music Player: Playing song"+i);
				Thread.sleep(3000); // Simulate the duration of playing a songs
			}
		} catch (InterruptedException e) { // the catch block prints the music
			System.out.println("Music Player: Interrupted while playing the Music");
		}
	}
}
