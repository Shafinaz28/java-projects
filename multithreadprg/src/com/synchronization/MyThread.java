package com.synchronization;

class MyThread extends Thread {

	private Counter counter;
	
	public MyThread(Counter counter) { //Constructor
		this.counter=counter;
	}
	
	@Override
	 public void run() {
		for(int i=0; i<1000; i++) {
			counter.increment();
		}
	}
}
