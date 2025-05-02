package com.multithreadprg;

 class Yield extends Thread {
	 public Yield(String name) {
		 super(name);
		
	}
	public void run() {
		for (int i=0;i<5;i++) {
			System.out.println(Thread.currentThread().getName()+ "is running");
			// Thread.yield();
		}
	}
	  public static void main(String[] args)throws InterruptedException {
		  Yield t1=new Yield("Thread-1");
		  Yield t2=new Yield("Thread-2");
		  t1.start();
		  t2.start();
	}
}
