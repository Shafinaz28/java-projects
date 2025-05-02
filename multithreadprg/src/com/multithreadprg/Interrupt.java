package com.multithreadprg;

 class Interrupt extends Thread {
  
	public void run() {
	try {
		Thread.sleep(1000);
		System.out.println("Thread is running...");
		
	} catch (InterruptedException e) {
		System.out.println("Thread interrupted: "+e);
	}
}
  public static void main(String[] args)throws InterruptedException {
	  Interrupt t1=new Interrupt();
	  t1.start();
	  t1.interrupt();
}
}
