package com.runnable;

public class MyRunnable implements Runnable  {

	public void run() {
		
		System.out.println("Runnung in " + Thread.currentThread().getName());
	}

}
