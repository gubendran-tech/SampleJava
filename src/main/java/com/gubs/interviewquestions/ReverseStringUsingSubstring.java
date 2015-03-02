/**
 * 
 */
package com.gubs.interviewquestions;

import java.util.Scanner;

/**
 * @author gubs
 *
 */
public class ReverseStringUsingSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// beginIndex is inclusive starts with 0
		// endIndex is exclusive endindex - 1 (Quick remember endIndex starts with 1 on string. beginINdex count starts with 0)
		System.out.println("burger".substring(1, 5));
		ReverseStringUsingSubstring reverseString = new ReverseStringUsingSubstring();
		reverseString.run();
	}

	public void run() {
		System.out.println("Enter your string to be reversed ");
		Scanner scan = new Scanner(System.in);
		String inputString = scan.nextLine();
		int index = inputString.length() - 1;
		
		while (index >= 0) {
			println(inputString.substring(index, index + 1));
			index--;
		}
	}

	public void println(String str) {
		System.out.println(str);
	}

}
