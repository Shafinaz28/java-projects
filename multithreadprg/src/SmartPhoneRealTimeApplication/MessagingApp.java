package SmartPhoneRealTimeApplication;

import java.util.concurrent.locks.Lock;

public class MessagingApp implements Runnable {
    private final Lock lock1;
    private final Lock lock2;

    public MessagingApp(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        lock2.lock();
        try {
            System.out.println("MessagingApp: Acquired lock2, waiting for lock1...");
            Thread.sleep(100); // Simulate some work
            lock1.lock();
            try {
                System.out.println("MessagingApp: Sending messages...");
            } finally {
                lock1.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println("MessagingApp: Interrupted.");
        } finally {
            lock2.unlock();
        }
    }
}
