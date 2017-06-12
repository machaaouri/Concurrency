package com.race.condition;

public class LongWrapper {
	
	private Object key =  new Object();
	private long value;
	
	public LongWrapper(long value)
	{
		this.value = value;
	}
	
	public long getValue()
	{
		return value;
	}
	
	/*
	 * this read is followed by a write operation
	 * i can be executed from different Threads which will cause a race condition
	 * to fix this , we create a key object and  synchronize this block of code
	 */
	
	public void incrementValue()
	{
		synchronized (key) {
			value += 1;
		}
	}
}
