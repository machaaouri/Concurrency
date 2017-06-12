package com.deadlocking;

public class DeadLock {
	
	/*
	 * Creating a possible dead lock
	 * if someone calls FuncA & FuncB at the same time there will be a dead lock unless we synchronize them
	 *
	 */
	
	private Object  key1 =  new Object();
	private Object  key2 =  new Object();
	
	public void FuncA(){
		
		synchronized (key1) {
			System.out.println("[" + Thread.currentThread().getName() + "] in FuncA()");
			FuncB();
		}
	}
	
	public void FuncB(){
		
		synchronized (key2) {
			System.out.println("[" + Thread.currentThread().getName() + "] in FuncB()");
			FuncC();
		}
	}
	
	public void FuncC(){
		
		synchronized (key2) {
			System.out.println("[" + Thread.currentThread().getName() + "] in FuncC()");
		}
	}
	
	

}
