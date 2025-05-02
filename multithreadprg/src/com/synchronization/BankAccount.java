package com.synchronization;


public class BankAccount {
	
	private int balance = 100;

    // Synchronized method to ensure thread safety
    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw" + amount);
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
            try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				
			}
            balance -= amount;
            System.out.println(Thread.currentThread().getName() +" complete withdrawal. Remaining balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() +" Insufficient balance");
        }
    }
}

//Main.java
