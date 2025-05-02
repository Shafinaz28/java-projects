package SmartPhoneRealTimeApplication;

public class AdLoader implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("AdLoader: Loading ad...");
            Thread.sleep(2000); // Simulate ad loading
            System.out.println("AdLoader: Ad loaded successfully.");
        } catch (InterruptedException e) {
            System.out.println("AdLoader: Interrupted while loading ads.");
        }
    }
}
