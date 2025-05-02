package multithreadprg;

class A
    {
	public synchronized void last()
	{
		System.out.println("Inside A, last() method");
	}
	public synchronized void d1(B b)
	{
		System.out.println("Thread start execution of d1() method");
	try 
	{
		Thread.sleep(2000);	
	}
	catch(InterruptedException e) 
	{
		System.out.println(e);
	}
	System.out.println("Thread trying to call B's last() method");
	b.last();
	}
    }
class B{
	 public synchronized void last()
	 {
		System.out.println("Inside B,last() method");
	 }
	 public synchronized void d2(A a)
	 {
		 System.out.println("Thread start execution of d2() method");
		 try 
		 {
			Thread.sleep(2000);
		 } 
		 catch (InterruptedException e)
		 {
			System.out.println(e);
		 }
		 System.out.println("Thread trying to call A's last() method");
			a.last();
	    }
       }
class threaddeadlock extends Thread{
	A a = new A();
	B b=new B();
	public void m1()
	{
	  this.start();
	  a.d1(b);
	}
	public void run()
	{
		b.d2(a);
	}
    public static void main (String[] args) {
	threaddeadlock deadlock = new threaddeadlock();
	deadlock.m1();
  }
}