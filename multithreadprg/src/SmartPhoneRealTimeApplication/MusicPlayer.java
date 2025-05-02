package SmartPhoneRealTimeApplication;

public class MusicPlayer extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Music Player: Playing song " + i);
                Thread.sleep(3000); // Simulating 3 seconds of playback
            }
        } catch (InterruptedException e) {
            System.out.println("Music Player: Interrupted while playing music.");
        }
    }
}

