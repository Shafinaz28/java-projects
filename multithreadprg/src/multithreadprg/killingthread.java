package multithreadprg;

class MyThread implements Runnable{
	private boolean exit;
	private String name;
	Thread t;
	MyThread(String threadname)
	{
		name = threadname;
		t=new Thread(this, name);
		System.out.println("New thread:"+t);
		exit=false;
		t.start();
	}
	public void run()
	{
		int i=0;
		while (!exit) {
			System.out.println(name+":"+i);
			i++;
			try 
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				System.out.println("Caught:"+e);
			}
		}
		System.out.println(name+"Stoppe.");
	}
	public void stop() 
	{
		exit=true;
	}
}

public class killingthread 
{
public static void main(String[] args)
{
	MyThread t1 = new MyThread("First thread");
	MyThread t2 = new MyThread("Seocnd thread");
	try 
	{
		Thread.sleep(500);
		t1.stop();
		t2.stop();
		Thread.sleep(500);
		
	} 
	catch (InterruptedException e)
	{
	System.out.println("Caught:"+e);
	}
	System.out.println("Exiting the main Thread");
}
}
