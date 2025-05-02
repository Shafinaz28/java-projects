package RealTimesSimualtion;

public class SmartPhoneSimulation {
    public static void main(String[] args) {
        System.out.println("Smartphone Simulation Started...");

        // Creating threads for each feature
        MusicPlayer musicPlayer = new MusicPlayer();
        NotificationReceiver notificationReceiver = new NotificationReceiver();
        InternetBrowser internetBrowser = new InternetBrowser();

        // Starting threads
        musicPlayer.start();
        notificationReceiver.start();
        internetBrowser.start();

        // Checking if threads are alive (thread utility function)
        try {
            if (musicPlayer.isAlive()) {
                System.out.println("Music Player thread is running.");
            }
            if (notificationReceiver.isAlive()) {
                System.out.println("Notification Receiver thread is running.");
            }
            if (internetBrowser.isAlive()) {
                System.out.println("Internet Browser thread is running.");
            }

            // Waiting for all threads to finish
            musicPlayer.join();
            notificationReceiver.join();
            internetBrowser.join();
        
        } 
        catch (InterruptedException e) {
        System.out.println("Main thread interrupted.");
        
        }

        // Checking thread states
        System.out.println("MusicPlayer thread state: " + musicPlayer.getState());
        System.out.println("NotificationReceiver thread state: " + notificationReceiver.getState());
        System.out.println("InternetBrowser thread state: " + internetBrowser.getState());
        
        System.out.println("Smartphone Simulation Ended.");
    }
}
