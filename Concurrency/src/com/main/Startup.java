package com.main;

import com.dead.locking.DeadLock;
import com.race.condition.LongWrapper;
import com.runnable.MyRunnable;

public class Startup {

	/*
	 * uncomment the Example you want to execute
	 */
	public Startup()
	{
		DeadLocking();
		//Race();
		//runnable();
	}
	
	/*
	 * Exemple of Runnnable 
	 */
	void runnable()
	{
		Thread t = new Thread(new MyRunnable());
		t.setName("My thread");
		t.start();
		
		//t.run(); if we call this method the task will not be executed by the thread we created (Thread)
		// it will be executed in the Main thread, run() should not be called
		
	}
	
	/*
	 * example of Race condition
	 */
	void Race()
	{
		final LongWrapper longWrapper =  new LongWrapper(0L);
		
		Runnable r = new Runnable() {
			
			public void run() {
				for(int i = 0 ; i < 1000 ; i++){
					longWrapper.incrementValue();
				}
			}
		};
		
		try {
			Thread[] threads = new Thread[1000];
			for(int i = 0 ; i<threads.length ; i++)
			{
				threads[i] =  new Thread(r);
				threads[i].start();
			}
			
			//Make sure they have all executd the Runnable correctly
			for(int i = 0 ; i<threads.length ; i++){ threads[i].join();}
			
			
			System.out.println("Value  = " + longWrapper.getValue());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	/*
	 * Dead lock example
	 */
	void DeadLocking()
	{
		final DeadLock deadLock =  new DeadLock();
	
		Runnable r1 = new Runnable(){
			public void run(){ deadLock.FuncA();}
		};
		
		Runnable r2 = new Runnable(){
			public void run(){ deadLock.FuncB();}
		};
		
		Thread t1 = new Thread(r1);
		t1.start();
		
		Thread t2 = new Thread(r2);
		t2.start();
		
		try {
			t1.join();
			t2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		
		 new Startup();
	}

}
