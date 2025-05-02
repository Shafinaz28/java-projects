package multithreadprg;
import java.util.Set;

 class My implements Runnable{
	public void run()
	{
     try 
     {
		Thread.sleep(2000);
	 }
     catch (Exception err) 
     {
		System.out.println(err);
	 }   		
	
     }
   }
  public class threadstates {
    public static void main (String[] args) 
    	throws Exception
    	{
    		for(int thread_num=0;thread_num<5;thread_num++)
    		{
    		  Thread t= new Thread(new Thread());
    		  t.setName("MyThread."+thread_num);
    		  t.start();
    		}
    		  Set<Thread>threadSet=Thread.getAllStackTraces().keySet();
    		  for(Thread t:threadSet)
    		  {
    			System.out.println("Thread:"+t+":"+"Thread status:"+t.getState());  
    		  }
    			
    		}
    	}