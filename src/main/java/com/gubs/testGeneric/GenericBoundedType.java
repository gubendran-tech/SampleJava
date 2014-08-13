package com.gubs.testGeneric;

import org.apache.log4j.Logger;

/**
 * 
 * @author gubs
 *
 */
public class GenericBoundedType {

  private static final Logger log = Logger.getLogger(GenericBoundedType.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Maximum elements in Integer " + 
				maximum(3, 4, 5));
		
		log.info("Maximum elements in Double " + 
				maximum(4.5, 7.9, 3.4));
		
		log.info("Maximum elements in Double " + 
				maximum("SaiTheja", "Gubs", "Kavi"));
	}

	private static <T extends Comparable<T>> T maximum(T i, T j, T k) {
		
		T max = i;
		if (j.compareTo(max) > 0) {
			max = j;
		} 
		
		if (k.compareTo(max) > 0) {
			max = k;
		}
		return max;
	}
}
