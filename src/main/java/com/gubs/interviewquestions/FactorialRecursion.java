/**
 * 
 */
package com.gubs.interviewquestions;

/**
 * @author gubs
 * 
 * http://www.toves.org/books/java/ch17-recur/index.html
 *
 */
public class FactorialRecursion {
	
	public static void main(String[] args) {
		FactorialRecursion fr = new FactorialRecursion();
		fr.run();
	}
	
	public void run() {
		int result = compute(4);
		println(result);
	}
	
	public void println(int result) {
		System.out.println(result);
	}
	
	public int compute(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * compute(n - 1); 
		}
	}

}
