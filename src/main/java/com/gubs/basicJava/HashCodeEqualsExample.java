/**
 * 
 */
package com.gubs.basicJava;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author gubs
 * 
 *         If key of the HashMap is self-defined objects, then equals() and
 *         hashCode() contract need to be followed. 
 *         
 *         If you want to delete the duplicate of the same object using HashSet need to have equals() and
 *         hashCode() method
 * 
 *         http://www.programcreek.com/2013/03/hashmap-vs-treemap-vs-hashtable-
 *         vs-linkedhashmap/
 * 
 */

// Default modified package-private (private with in the package)
class HDTV2 {
	
	private int size;
	private String brand;
	private String location;

	// Constructor
	HDTV2(int size, String brand, String location) {
		this.size = size;
		this.brand = brand;
		this.setLocation(location);
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
		return "HDTV2 [size=" + size + ", brand=" + brand + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HDTV2 other = (HDTV2) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}

public class HashCodeEqualsExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HDTV2 obj1 = new HDTV2(40, "Samsung", "Jersey City");
		HDTV2 obj2 = new HDTV2(55, "Sony", "Jersey City");
		HDTV2 obj3 = new HDTV2(40, "Samsung", "Fords City");
		
		Set<HDTV2> hdtvSet = new HashSet<HDTV2>();
		hdtvSet.add(obj1);
		hdtvSet.add(obj2);
		hdtvSet.add(obj3);
		
		// Duplicate objects on Set (HDTV2 obj) is avoided with hashCode and equals method.
		// If the object is missing after you push into Set or HashMap key check your hashCode or equals method. 
		// You must not considered the new attributes and it may cause issue
		System.out.println(hdtvSet.toString());
		
		Map<Integer, HDTV2> mapHDTV = new HashMap<Integer, HDTV2>();
		mapHDTV.put(0, obj1);
		mapHDTV.put(1, obj2);
		mapHDTV.put(2, obj3);
		
		for (Map.Entry<Integer, HDTV2> map1 : mapHDTV.entrySet()) {
			System.out.println(map1.getKey() + " " + map1.getValue());
		}
	}

}
