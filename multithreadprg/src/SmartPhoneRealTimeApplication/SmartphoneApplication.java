package SmartPhoneRealTimeApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SmartphoneApplication {
    // Database connection details
    private static final String URL = "jdbc:postgresql://localhost:5432/smartphone_app";
    private static final String USER = "postgres"; // Change to your PostgreSQL username
    private static final String PASSWORD = "admin123"; // Change to your PostgreSQL password

    public static void main(String[] args) {
        // Thread pool for managing threads
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // Locks for synchronization and deadlock demonstration
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        // Initialize threads
        MusicPlayer musicPlayer = new MusicPlayer();
        NotificationReceiver notificationReceiver = new NotificationReceiver();
        CallManager callManager = new CallManager(lock1, lock2);
        MessagingApp messagingApp = new MessagingApp(lock1, lock2);
        InternetBrowser internetBrowser = new InternetBrowser();

        // Start Daemon Thread
        musicPlayer.setDaemon(true);
        musicPlayer.start();

        // Log activity to the database
        logActivityToDatabase("MusicPlayer thread started (Daemon)");

        // Start other threads
        notificationReceiver.start();
        logActivityToDatabase("NotificationReceiver thread started");

        threadPool.submit(callManager);
        logActivityToDatabase("CallManager thread submitted to thread pool");

        threadPool.submit(messagingApp);
        logActivityToDatabase("MessagingApp thread submitted to thread pool");

        threadPool.submit(internetBrowser);
        logActivityToDatabase("InternetBrowser thread submitted to thread pool");

        // Shutdown thread pool when done
        threadPool.shutdown();
    }

    // Method to log thread activities to the database
    private static void logActivityToDatabase(String activity) {
        String sql = "INSERT INTO thread_logs (action) VALUES (?)"; // Assuming table exists
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
