package com.runnable;

public class Startup {

	
	public Startup()
	{
		first();
	}
	
	void first()
	{
		Thread t = new Thread(new MyRunnable());
		t.setName("My thread");
		t.start();
		
		//t.run(); if we call this method the task will not be executed by the thread we created (Thread)
		// it will be executed in the Main thread, run() should not be called
		
	}

	public static void main(String[] args) {
		
		 new Startup();
	}

}
