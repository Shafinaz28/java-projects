package com.multithreadprg;


class Threadlifecycle extends Thread {
	@Override
	public void run() {
	 {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		
	}
	}
	public static void main(String[] args) throws InterruptedException {
	    Threadlifecycle t1 = new Threadlifecycle();
	    t1.start();
	    t1.join();
	    System.out.println("Hello");
	}
}
// start run sleep join setPriority




