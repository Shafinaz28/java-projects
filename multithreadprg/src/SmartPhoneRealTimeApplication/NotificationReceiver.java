package SmartPhoneRealTimeApplication;

public class NotificationReceiver extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                if (Thread.interrupted()) {
                    System.out.println("Notification Receiver: Interrupted. Stopping notifications...");
                    return;
                }
                System.out.println("Notification Receiver: New notification received.");
                Thread.sleep(2000); // Simulating 2 seconds between notifications
            }
        } catch (InterruptedException e) {
            System.out.println("Notification Receiver: Interrupted while receiving notifications.");
        }
    }
}
