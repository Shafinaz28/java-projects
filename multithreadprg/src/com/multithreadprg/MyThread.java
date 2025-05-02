package com.multithreadprg;

public class MyThread {
	  public static void main(String[] args) {
	        World world = new World();// Create an instance of World
	        Thread t1=new Thread(world);
	        t1.start(); // Start the thread

	        // Main thread loop
	        for (int i = 0; i < 10; i++) {
	            System.out.println("Hello");
	        }
	    }
}
