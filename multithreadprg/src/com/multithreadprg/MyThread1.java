package com.multithreadprg;

class MyThread1 extends Thread{
	
	@Override
	public void run() {
	System.out.println("RUNNING");
	try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		
		System.out.println(e);
	}
}
    public static void main(String[] args) throws InterruptedException{
	MyThread1 t1 = new MyThread1();
	System.out.println(t1.getState());
	t1.start();
	System.out.println(t1.getState());
	Thread.sleep(100);
	System.out.println(t1.getState());
	t1.join();
	System.out.println(t1.getState());
	
	
   }
}
