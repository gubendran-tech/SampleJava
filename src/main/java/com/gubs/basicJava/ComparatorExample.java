/**
 * 
 */
package com.gubs.basicJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author gubs
 * 
 *         http://www.programcreek.com/2011/12/examples-to-demonstrate-comparable-vs-comparator-in-java/
 * 
 *         Comparator is capable of comparing two different objects. The method
 *         required for implementation is compare().
 *         
 *         The Default class access modifier is package-private (i.e DEFAULT) and it is visible only from the same package.
 */

class HDTV1 implements Comparable<HDTV1>{
	private int size;
	private String brand;

	HDTV1(int size, String brand) {
		this.size = size;
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
	public String toString() {
		return "HDTV1 [size=" + size + ", brand=" + brand + "]";
	}

	@Override
	public int compareTo(HDTV1 obj) {
		
		if (this.size > obj.size) {
			return 1;
		} else if (this.size < obj.size) {
			return -1;
		}
		return 0;
	}
}

/**
 * This class is for comparator implementation like Sort feature
 * @author gubs
 *
 */
class sizeComparator implements Comparator<HDTV1> {

	@Override
	public int compare(HDTV1 obj1, HDTV1 obj2) {

		if (obj1.getSize() > obj2.getSize()) {
			return 1;
		} else if (obj1.getSize() < obj2.getSize()) {
			return -1;
		}
		
		return 0;
	}
	
}

public class ComparatorExample {

	/**
	 * http://www.programcreek.com/2011/12/examples-to-demonstrate-comparable-vs
	 * -comparator-in-java/
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HDTV1 obj1 = new HDTV1(55, "Sony");
		HDTV1 obj2 = new HDTV1(40, "Samsung");
		HDTV1 obj3 = new HDTV1(70, "google");
		HDTV1 obj4 = new HDTV1(60, "Panasonic");
		
		List<HDTV1> hdtvComparator = new ArrayList<HDTV1>();
		hdtvComparator.add(obj1);
		hdtvComparator.add(obj2);
		hdtvComparator.add(obj3);
		hdtvComparator.add(obj4);
		
		// Sort will happen based on the comparator we passed based on compare method
		Collections.sort(hdtvComparator, new sizeComparator());
		
		System.out.println(hdtvComparator.toString());
		
		List<HDTV1> hdtvComparable = new ArrayList<HDTV1>();
		hdtvComparable.add(obj1);
		hdtvComparable.add(obj2);
		hdtvComparable.add(obj3);
		hdtvComparable.add(obj4);
		
		// Sort will happen based on the comparable class implementation in the list contained Object
		Collections.sort(hdtvComparable);
		
		System.out.println(hdtvComparable.toString());
		
	}

}
