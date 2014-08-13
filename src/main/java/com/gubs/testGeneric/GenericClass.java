/**
 * 
 */
package com.gubs.testGeneric;

import org.apache.log4j.Logger;

/**
 * @author gubs
 *
 */
public class GenericClass<T> {

  private static final Logger log = Logger.getLogger(GenericClass.class);

	private T t;
	
	public void add(T t) {
		this.t = t;
	}
	
	public T get() {
		return this.t;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericClass<Integer> integerGeneric = new GenericClass<Integer>();
		GenericClass<String> stringGeneric = new GenericClass<String>();
		
		integerGeneric.add(new Integer(15));
		stringGeneric.add(new String("SaiTheja Gubendran"));
		
		log.info("Integer Class " + integerGeneric.get());
		log.info("String class " + stringGeneric.get());
	}

}
