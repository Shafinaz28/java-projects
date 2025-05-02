package com.multithreadprg;

public class SetPriority extends Thread {
	public SetPriority(String name) {
		super(name);
	}
	@Override
	public void run() {
		 {
			for(int i=0;i<5;i++) {
				String a =" ";
				for(int j=0;j< 1;j++) {
					a += "a";
					
					System.out.println(Thread.currentThread().getName()+ " -Priority:"+Thread.currentThread().getPriority()+"-count:"+ i);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				
			}
			}
			}
		 }
		}
		public static void main(String[] args) throws InterruptedException {
			SetPriority l = new SetPriority("low Priority Thread");
			SetPriority m = new SetPriority("medium Priority Thread");
			SetPriority h = new SetPriority("high Priority Thread");
			l.setPriority(Thread.MIN_PRIORITY);
			m.setPriority(Thread.NORM_PRIORITY);
			h.setPriority(Thread.MAX_PRIORITY);
			l.start();
			m.start();
			h.start();
			
}
}


// start run sleep join setPriority