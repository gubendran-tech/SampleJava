/**
 * 
 */
package com.gubs.interviewquestions;

import java.util.Scanner;

/**
 * @author gubs
 * 
 * Anagrams -> name formed by re-arranging the letters of another, such as cinema formed from iceman
 * 
 * Ex : if we take east we can produce 24 combination of words 
 *  
 *  http://www.toves.org/books/java/ch18-recurex/index.html
 *
 */
public class Anagrams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Anagrams anagrams = new Anagrams();
		anagrams.run();

	}
	
	public void run() {
		System.out.println("Enter your word to be anagram");
		Scanner scan = new Scanner(System.in);
		String inputWord = scan.nextLine();
		printAnagram("", inputWord);
	}
	
	public void printAnagram(String prefix, String word) {
		if (word.length() <= 1) {
			println(prefix + word);
		} else {
			for (int i =0 ;i < word.length(); i++) {
				String cur = word.substring(i, i + 1);
				String beforeCur = word.substring(0, i); //letter before cur
				String afterCur = word.substring(i + 1); // letter after cur
				printAnagram(prefix + cur, beforeCur + afterCur);
			}
		}
	}	
	
	public void println(String word) {
		System.out.println(word);
	}
	

}
