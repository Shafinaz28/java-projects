package SmartPhoneRealTimeApplication;


import java.util.concurrent.locks.Lock;

public class CallManager implements Runnable {
    private final Lock lock1;
    private final Lock lock2;

    public CallManager(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        lock1.lock();
        try {
            System.out.println("CallManager: Acquired lock1, waiting for lock2...");
            Thread.sleep(100); // Simulate some work
            lock2.lock();
            try {
                System.out.println("CallManager: Making a call...");
            } finally {
                lock2.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println("CallManager: Interrupted.");
        } finally {
            lock1.unlock();
        }
    }
}
