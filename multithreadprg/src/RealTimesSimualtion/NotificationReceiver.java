package RealTimesSimualtion;

public class NotificationReceiver extends Thread {
     
	public void run() {
		try {
			for(int i =1; i<=5; i++) {   // the for loop iterates 5 times, printing a message indicating a new notification.
				System.out.println("Notification: New Message Received!");
				Thread.sleep(2000); // Simulate the notification intervals
			}
		} catch (InterruptedException e) { // Thread is interrupted during sleep
			System.out.println("Notifiaction Recevier: Interrupted while receving notification.");
		}
	}
}
