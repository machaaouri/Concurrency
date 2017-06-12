package com.falsesharing;

public class FalseSharing implements Runnable {

	public final static int NUM_THREADS = 4; 
    public final static long ITERATIONS = 50000000; // 50 millions
    private final int arrayIndex;
    
    private static volatilePadded[] paddedArray;
    private static volatileUnPadded[] UnpaddedArray;
    
    
    
    public FalseSharing(final int arrayIndex)
    {
        this.arrayIndex = arrayIndex;
    }
    
    public final static class volatilePadded{
    	
    	/*
    	 * Every object is aligned to an 8-byte granularity 
    	 * boundary for performance
    	 * we can pad a cache line between any fields with 7 longs
    	 */
    	
    	public long l1,l2,l3,l4,l5,l6;
    	public volatile long paddedlong = 0;
    }
    
    public final static class volatileUnPadded{
    	
    	public volatile long paddedlong = 0;
    }
    
    static {
    	paddedArray = new volatilePadded[NUM_THREADS];
    	for(int i = 0  ; i <paddedArray.length; i++) {
    		paddedArray[i] = new volatilePadded();
    	}
    	
    	UnpaddedArray = new volatileUnPadded[NUM_THREADS];
    	for(int i = 0  ; i <UnpaddedArray.length; i++) {
    		UnpaddedArray[i] = new volatileUnPadded();
    	}
    	
    }
    
    public static void Test() throws InterruptedException
    {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads)
        {
            t.start();
        }

        for (Thread t : threads)
        {
            t.join();
        }

    }

	public void run() {

	        long i = ITERATIONS + 1;
	        while (0 != --i)
	        {
	        	/*
	        	 * comment out the one you want to test
	        	 */
	            //paddedArray[arrayIndex].paddedlong = i;
	            UnpaddedArray[arrayIndex].paddedlong = i; 
	        }
	}
    
    
}
