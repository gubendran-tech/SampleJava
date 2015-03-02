/**
 * 
 */
package com.gubs.interviewquestions;

import java.util.Scanner;

/**
 * @author gubs
 *
 * Perfect numbers are 6, 28 etc..
 *   1,2,3 => 6; 1, 2, 4, 7 14 => 28
 *   
 *   www.toves.org/books/java/ch17-recur/index.html
 *   
 */
public class PerfectNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Input your number to validate perfect number ..");
		Scanner scan = new Scanner(System.in);
		int query = scan.nextInt();
		int index = 1;
		int sum = 0;
		while (index < query) {
			// 28 % 1 => 0; 28%2 => 0; 28%3 => 9 quotient 1 => remainder. 28%3 => 1 
			// Mod operator is left divided by right and the quotient goes away and remainder is answer
			if (query % index == 0) {
				sum += index;
			}
		index++;
		}
		
		if (sum == query) {
			System.out.println("Its a perfect number");
		} else {
			System.out.println("Its not a perfect number");
		}
		
	}
	
}
