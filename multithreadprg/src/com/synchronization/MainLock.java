package com.synchronization;

public class MainLock {
    public static void main(String[] args) {
        Lock account = new Lock(); // Class is now named Lock

        Runnable task = () -> account.withdraw(50);

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
