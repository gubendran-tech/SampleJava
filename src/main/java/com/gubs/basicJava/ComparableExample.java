/**
 * 
 */
package com.gubs.basicJava;

/**
 * @author gubs
 *         http://www.programcreek.com/2011/12/examples-to-demonstrate-comparable-vs-comparator-in-java/
 * 
 *         Comparable is implemented by a class in order to be able to comparing
 *         object of itself with some other objects.The method required for
 *         implementation is compareTo().
 */

 class HDTV implements Comparable<HDTV> {

	private int size;
	private String brand;

	 HDTV(int size, String brand) {
		this(size);
		this.brand = brand;
	}

	 HDTV(int size) {
		this.size = size;
	}

	 HDTV(String brand) {
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public int compareTo(HDTV obj) {
		if (this.size > obj.size) {
			return 1;
		} else if (this.size < obj.size) {
			return -1;
		}
		return 0;
	}

}

public class ComparableExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HDTV tv1 = new HDTV(40, "Samsung");
		HDTV tv2 = new HDTV(55, "Sony");

		if (tv1.compareTo(tv2) > 0) {
			System.out.println(tv1.getBrand() + " is Greater");
		} else {
			System.out.println(tv2.getBrand() + " is Greater");
		}
	}

}
