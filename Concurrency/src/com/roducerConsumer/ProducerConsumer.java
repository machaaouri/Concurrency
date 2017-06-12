package com.roducerConsumer;

public class ProducerConsumer {

	
	private static Object  key =  new Object();
	
	private static int[] buffer;
	private static int count;
	
	
	public static void Init(int size)
	{
		buffer =  new int[size];
		count = 0;
	}
	
	public static class Producer {
		
		public void produce() {
			
			synchronized (key) {
				if(isFull(buffer)){ 
					try {
						key.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				buffer[count++] = 1;
				key.notifyAll();
			}
		}
	}
	
	public static class Consumer {
		
		public void consume (){
			synchronized (key) {
				if(isEmpty(buffer)){
					
					try {
						key.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				buffer[--count] = 0;
				key.notifyAll();
			}
		}
	}
	
	static boolean isEmpty(int[] buffer){
		return count == 0;
	}
	
	static boolean isFull(int[] buffer){
		return count == buffer.length;
	}
	
	public static int Count()
	{
		return count;
	}
}
