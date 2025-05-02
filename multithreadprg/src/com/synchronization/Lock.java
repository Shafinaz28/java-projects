package com.synchronization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Lock { // Renamed class to Lock
    
	private int balance = 100;
   
    private final ReentrantLock lock = new ReentrantLock(); // Avoid name conflict

    public void withdraw(int amount) {
        
    	System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) { // Corrected tryLock syntax
                try {
                    if (balance >= amount) {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(3000); // Simulate time taken to process the withdrawal
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() +
                                " completed withdrawal. Remaining balance: " + balance);
                    } else {
                        System.out.println(Thread.currentThread().getName() + " insufficient balance");
                    }
                } finally {
                    lock.unlock(); // Ensure the lock is released in all cases
                }
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " could not acquire the lock, will try later");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() +
                    " was interrupted during withdrawal: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
        if (Thread.currentThread().isInterrupted()) {
		System.out.println("");	
		}
    }
}
