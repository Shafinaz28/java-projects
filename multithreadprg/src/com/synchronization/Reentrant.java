package com.synchronization;

import java.util.concurrent.locks.ReentrantLock;

public class Reentrant {
    private final ReentrantLock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock(); // Acquire lock
        try {
            System.out.println("Outer method");
            innerMethod(); // Call innerMethod
        } finally {
             // Release lock
        }
    }

    public void innerMethod() {
        lock.lock(); // Acquire lock
        try {
            System.out.println("Inner method");

        } finally {
            lock.unlock(); // Release lock
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Reentrant example = new Reentrant();
        example.outerMethod();
    }
}
