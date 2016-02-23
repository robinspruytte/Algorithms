package algorithms;

import java.util.Arrays;

public class Fibonacci {
	private static int memoized []; 
	
	public static int fib(int n){
		if(n == 0 || n == 1) return n;
		return fib(n-1) + fib(n-2);
	}
	
	/**
	 * Initializes the memoized version of fib
	 * @param n the nth number in the row of fib
	 * @return the nth number in the row of fib.
	 */
	
	public static int fibm(int n){
		//First time
		if(memoized == null) 
			memoized = new int[n+1];
		//If n is larger then previous time
		if(n >= memoized.length)
			memoized = Arrays.copyOf(memoized, n+1);
		return fib_m(n);
	}
	
	/**
	 * Recursion of the memoized fib		
	 * @param n the nth number
	 * @return the nth number
	 */
	private static int fib_m(int n){
		//Trivial
		if(n == 0 || n == 1) return n;
		//Calculated before
		if(memoized[n] != 0) return memoized[n];
		//Calculate new value
		memoized[n] = fib_m(n-1) + fib_m(n-2);
		return memoized[n];
	}

}
