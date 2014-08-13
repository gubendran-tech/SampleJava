package com.gubs.testGeneric;

import org.apache.log4j.Logger;

/**
 * 
 * @author gubs
 *
 */
public class GenericMethodTest {

  private static final Logger log = Logger.getLogger(GenericMethodTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	   // Print Generic Integer Array
	   Integer[] intArray = {1, 2, 3, 4};
	   printGenericArray(intArray);
	   
	   // Print Generic Double Array
	   Double[] doubleArray = {1.2, 2.2, 5.5};
	   printGenericArray(doubleArray);
	   
	   // Print String Array
	   String[] stringArray = {"Gubs", "Kavitha", "SaiTheja"};
	   printGenericArray(stringArray);

	}

	private static <E> void printGenericArray(E[] elements) {
		for (E element : elements) {
			log.info("The output element is " + element);
		}
	}
}
