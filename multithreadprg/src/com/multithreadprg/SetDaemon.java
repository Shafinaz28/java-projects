package com.multithreadprg;

class SetDaemon extends Thread {
	
  public void run() {
	  while(true) {
		  System.out.println("Hello World!");
  }
  }
  
  
  public static void main(String[] args){
	  SetDaemon myThread =new SetDaemon();
	  myThread.setDaemon(true);
	  SetDaemon t1 = new SetDaemon();
	  t1.start();
	  myThread.start();
	  System.out.println("Main done");
	  }
}
