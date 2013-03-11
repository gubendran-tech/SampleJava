/**
 * 
 */
package com.gubs.testGeneric;

/**
 * @author gubs
 *
 */
public class GenericClass<T> {

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
		
		System.out.println("Integer Class " + integerGeneric.get());
		System.out.println("String class " + stringGeneric.get());
	}

}
